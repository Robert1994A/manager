package ro.certificate.manager.service.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.DHParameterSpec;
import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ro.certificate.manager.entity.Keystore;
import ro.certificate.manager.entity.User;
import ro.certificate.manager.service.KeystoreService;
import ro.certificate.manager.wrapper.Certificate;
import ro.certificate.manager.wrapper.CertificateDetails;

@Component
public class CertificateUtils {

	@Autowired
	private StringGenerator stringGenerator;

	@Autowired
	private KeystoreService keystoreService;

	private String keystoreFolder = "C:\\Users\\Robert\\Desktop\\keyStore";

	private static final String KEYSTORE_TYPE = "JKS";

	/**
	 * Date format for ISO 8601.
	 */
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	/**
	 * Generate a certificate using details from interface.
	 * 
	 * @param certificate
	 * @param user
	 * @throws Exception
	 */
	public void generateCertificate(Certificate certificate, User user) throws Exception {

		String commonName = certificate.getCommonName();

		String keyStorePassword = stringGenerator.getRandomString();

		char[] keyStorPasswordChars = keyStorePassword.toCharArray();

		// Create an empty keyStore on disk.
		File keyStoreFile = createKeyStore(commonName, user.getId(), keyStorePassword, true);

		FileOutputStream fileOutputStream = null;

		try {
			// Security.addProvider(new BouncyCastleProvider());

			KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);

			// Generate new key pair.
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(certificate.getEncryptionAlgorithm());
			keyPairGenerator.initialize(certificate.getKeySize(), new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			// Create subject based on provided certificate parameters such as:
			// OU, O, E etc.
			X500Name name = getX500Name(certificate);

			Date currentDate = new Date();

			Date notAfter = calculateNotAfter(certificate.getValidityNumber(), certificate.getValidityType());

			BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());

			// Create new certificate.
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(name, serial, currentDate, notAfter,
					name, keyPair.getPublic());
			ContentSigner sigGen = new JcaContentSignerBuilder(certificate.getSignatureAlgorithm())
					.build(keyPair.getPrivate());
			X509Certificate cert = new JcaX509CertificateConverter().getCertificate(certGen.build(sigGen));

			// Validate created certificate.
			cert.checkValidity();
			cert.verify(cert.getPublicKey());

			keyStore.load(null);

			// Save generated key pair and certificate in created keyStore.
			keyStore.setKeyEntry(commonName, keyPair.getPrivate(), keyStorPasswordChars,
					new java.security.cert.Certificate[] { cert });
			fileOutputStream = new FileOutputStream(keyStoreFile);

			Keystore keystore = new Keystore();
			keystore.setCertificateSubject(commonName);
			keystore.setPassword(AES.encrypt(keyStorePassword));
			keystore.setName(keyStoreFile.getName());
			keystore.setUser(user);
			keystore.setCreationDate(new Date());
			// Save created keyStore in database.
			keystore = keystoreService.save(keystore);

			// Save on disk created keyStore.
			keyStore.store(fileOutputStream, keyStorPasswordChars);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (Exception e2) {
				// Silent.
			}
		}
	}

	/**
	 * Import a certificate based on his content and certificate key in a new
	 * keyStore generated used provided details such as alias, filename and
	 * password.
	 * 
	 * @param certificatePEM
	 *            The content of certificate and include -----BEGIN
	 *            CERTIFICATE----- and -----END CERTIFICATE-----.
	 * @param privateKeyPEM
	 *            The content of private key and include -----BEGIN RSA PRIVATE
	 *            KEY----- and -----END RSA PRIVATE KEY-----.
	 * @param privateKeyDERStream
	 * @param certificateDERStream
	 * 
	 * @throws Exception
	 *             If a error occurs.
	 */
	public void importCertificate(String certificatePEM, String privateKeyPEM, byte[] certificateDERStream,
			byte[] privateKeyDERStream, User user) throws Exception {
		FileOutputStream fileOutputStream = null;
		try {

			java.security.cert.Certificate[] chain = extractCertificates(certificatePEM, certificateDERStream);

			String commonName = getCommonName((X509Certificate) chain[0]);

			PrivateKey privateKey = extractPrivateKey(privateKeyPEM, privateKeyDERStream);

			KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
			keyStore.load(null);
			String keyStorePassword1 = stringGenerator.getRandomString();
			char[] passwordChars = keyStorePassword1.toCharArray();

			keyStore.setKeyEntry(commonName, privateKey, passwordChars, chain);

			File keyStoreFile = createKeyStore(commonName, user.getId(), keyStorePassword1, true);
			fileOutputStream = new FileOutputStream(keyStoreFile);

			Keystore keystore = new Keystore();
			keystore.setCertificateSubject(commonName);
			keystore.setPassword(AES.encrypt(keyStorePassword1));
			keystore.setName(keyStoreFile.getName());
			keystore.setUser(user);
			keystore.setCreationDate(new Date());
			keystore = keystoreService.save(keystore);

			keyStore.store(fileOutputStream, passwordChars);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (Exception e) {
				// Silent.
			}
		}
	}

	/**
	 * Extract private key from PEM format or DER format.
	 * 
	 * @param privateKeyPEM
	 *            Private key in PEM format.
	 * @param privateKeyDERStream
	 *            Private key in DER format.
	 * @return extracted private key if is found, null otherwise.
	 * @throws Exception
	 *             If a error occurs.
	 */
	private PrivateKey extractPrivateKey(String privateKeyPEM, byte[] privateKeyDERStream) throws Exception {
		PEMParser parser = null;
		PrivateKey privateKey = null;
		try {
			if (privateKeyPEM != null) {
				parser = new PEMParser(new StringReader(privateKeyPEM));
				Object returnedObject = parser.readObject();
				privateKey = objectToPrivateKey(returnedObject);
			} else {
				PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyDERStream);
				KeyFactory keyFactory = null;
				try {
					try {
						keyFactory = KeyFactory.getInstance("RSA");
						privateKey = keyFactory.generatePrivate(keySpec);
					} catch (InvalidKeySpecException e) {
						keyFactory = KeyFactory.getInstance("EC");
						privateKey = keyFactory.generatePrivate(keySpec);
					}
				} catch (Exception e) {
					parser = new PEMParser(new StringReader(new String(privateKeyDERStream)));
					Object returnedObject = parser.readObject();
					privateKey = objectToPrivateKey(returnedObject);
				}
			}
		} finally {
			try {
				if (parser != null) {
					parser.close();
				}
			} catch (Exception e) {
				// Do nothing.
			}
		}

		if (privateKey == null) {
			throw new Exception("No private key was found.");
		}

		return privateKey;
	}

	/**
	 * Convert object returned by pem parser in private key object.
	 * 
	 * @param object
	 *            The object who will be converted at private key.
	 * @return resulted private key.
	 * @throws Exception
	 */
	private PrivateKey objectToPrivateKey(Object object) throws Exception {
		PrivateKey privateKey = null;
		if (object != null) {
			if (object instanceof PrivateKeyInfo) {
				PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) object;
				byte[] encoded = privateKeyInfo.getEncoded();
				KeyFactory kf = null;
				try {
					kf = KeyFactory.getInstance("RSA");
					privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
				} catch (InvalidKeySpecException e) {
					kf = KeyFactory.getInstance("EC");
					privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
				}
			} else if (object instanceof PEMKeyPair) {
				PEMKeyPair pemKeyPair = (PEMKeyPair) object;
				privateKey = new JcaPEMKeyConverter().getPrivateKey(pemKeyPair.getPrivateKeyInfo());
			}
		}

		return privateKey;
	}

	/**
	 * Extract certificate chain from PEM format or DER format.
	 * 
	 * @param certificatePEM
	 *            Certificates in PEM format.(String)
	 * @param certificateDER
	 *            Certificates in DER format.(Binary)
	 * 
	 * @return extracted chain from provided data, null otherwise.
	 * @throws Exception
	 *             If a error occurs.
	 */
	private java.security.cert.Certificate[] extractCertificates(String certificatePEM, byte[] certificateDER)
			throws Exception {
		java.security.cert.Certificate[] chain = null;
		InputStream inputStream = null;
		try {
			if (certificatePEM != null) {
				inputStream = new ByteArrayInputStream(certificatePEM.getBytes());
			} else {
				inputStream = new ByteArrayInputStream(certificateDER);
			}

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Collection<?> c = cf.generateCertificates(inputStream);
			int numberOfCertificates = c.size();
			if (numberOfCertificates == 0) {
				throw new Exception("No certificate was found.");
			}
			chain = new java.security.cert.Certificate[numberOfCertificates];
			Iterator<?> i = c.iterator();
			int counter = 0;
			while (i.hasNext()) {
				X509Certificate cert509 = (X509Certificate) i.next();
				// Check if introduced certificate is valid. If is not then a
				// exception is thrown and the message will be visible in
				// interface.
				cert509.checkValidity();
				chain[counter] = cert509;
				counter++;
			}

			return chain;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * Get common name for given certificate as string.
	 * 
	 * @param x509Certificate
	 *            The certificate from we extract common name.
	 * @return common name of given certificate.
	 */
	private String getCommonName(X509Certificate x509Certificate) {
		String commonName = null;

		try {
			X500Name x500name = new JcaX509CertificateHolder(x509Certificate).getSubject();
			RDN cn = x500name.getRDNs(BCStyle.CN)[0];
			commonName = IETFUtils.valueToString(cn.getFirst().getValue());
		} catch (Exception e) {
			// Do nothing.
		}

		return commonName;
	}

	/**
	 * Create a empty keyStore file and throw exception if file already exist.
	 * 
	 * @param keyStoreName
	 *            The name of keyStore file.
	 * @param keyStorePassword
	 *            The password of keyStore.
	 * @return created keyStore file.
	 * @throws Exception
	 *             If keyStore file already exist.
	 */
	private File createKeyStore(String keyStoreName, String userID, String keyStorePassword, boolean create)
			throws Exception {
		// Check if keyStore exist on disk.
		File userKeyStore = new File(keystoreFolder, userID);
		if (!userKeyStore.exists()) {
			userKeyStore.mkdir();
		}
		keyStoreName = keyStoreName.replace(".", "");
		keyStoreName = keyStoreName.replace("/", "");
		keyStoreName = keyStoreName.replace("\\", "");
		keyStoreName = keyStoreName.replace("*", "");
		keyStoreName = keyStoreName.replace("?", "");
		keyStoreName = keyStoreName.replace("<", "");
		keyStoreName = keyStoreName.replace(">", "");
		keyStoreName = keyStoreName.replace("|", "");

		keyStoreName = keyStoreName + "_" + System.currentTimeMillis() + "_" + stringGenerator.generateRandomNumber();

		File keyStoreFile = new File(userKeyStore, keyStoreName + ".keystore");

		if (create) {
			if (keyStoreFile.exists()) {
				throw new Exception(
						"The certificate with " + keyStoreFile.getAbsolutePath() + " path already exist on disk.");
			}
		} else {
			if (!keyStoreFile.exists()) {
				throw new Exception("The certificate with " + keyStoreFile.getName() + " name not exist on disk.");
			}
		}

		return keyStoreFile;
	}

	/**
	 * Calculate last date of validity for a certificate, basically expired
	 * date.
	 * 
	 * @param certificateValidity
	 *            Validity number. Represents the number of days, months or
	 *            years.
	 * @param certificateValidityType
	 *            Represent the type of validity and values can be: year, month
	 *            or day.
	 * @return expired date for a certificate.
	 * @throws Exception
	 *             If difference between current date and resulted date is more
	 *             than 25 years.
	 */
	private Date calculateNotAfter(int certificateValidity, String certificateValidityType) {
		Calendar calendar = Calendar.getInstance();
		if (certificateValidityType.toUpperCase().equals("YEARS")) {
			calendar.add(Calendar.YEAR, certificateValidity);
		} else if (certificateValidityType.toUpperCase().equals("MONTHS")) {
			calendar.add(Calendar.MONTH, certificateValidity);
		} else if (certificateValidityType.toUpperCase().equals("DAYS")) {
			calendar.add(Calendar.DAY_OF_MONTH, certificateValidity);
		} else {
			// Default value is 1 year.
			calendar.add(Calendar.YEAR, 1);
		}

		return calendar.getTime();
	}

	/**
	 * Get a X500Name based on certificate parameters such as CN, OU, O etc.
	 * 
	 * @param certificateParameters
	 *            The certificate parameters such as OU, O etc.
	 * @return a object formed by given parameters.
	 */
	private X500Name getX500Name(Certificate certificate) {

		X500NameBuilder nameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
		// I don't made test if common name is null because this field is
		// mandatory.
		nameBuilder.addRDN(BCStyle.CN, certificate.getCommonName());

		String organizationUnit = certificate.getOrganizationUnit();
		if (organizationUnit != null && organizationUnit.trim().length() > 0) {
			nameBuilder.addRDN(BCStyle.OU, organizationUnit);
		}

		String organization = certificate.getOrganization();
		if (organization != null && organization.trim().length() > 0) {
			nameBuilder.addRDN(BCStyle.O, organization);
		}

		String locality = certificate.getLocality();
		if (locality != null && locality.trim().length() > 0) {
			nameBuilder.addRDN(BCStyle.L, locality);
		}

		String state = certificate.getState();
		if (state != null && state.trim().length() > 0) {
			nameBuilder.addRDN(BCStyle.ST, state);
		}

		String country = certificate.getCountry();
		if (country != null && country.trim().length() > 0) {
			nameBuilder.addRDN(BCStyle.C, country);
		}

		String email = certificate.getEmail();
		if (email != null && email.trim().length() > 0) {
			nameBuilder.addRDN(BCStyle.E, email);
		}

		return nameBuilder.build();
	}

	/**
	 * Get certificate information as a XML structure.
	 * 
	 * @param document
	 *            Used to create tags.
	 * @param findedKeystore
	 *            The certificate from who extract necessary information.
	 * @param user
	 *            Certificate alias.
	 * 
	 * @return Element with certificate info as a XML structure.
	 */
	public List<CertificateDetails> getCertificatesInfo(Keystore findedKeystore, User user) throws Exception {

		String password = AES.decrypt(findedKeystore.getPassword());

		File keyStoreFile = getKeyStoreFile(user.getId(), findedKeystore.getName());

		KeyStore keyStore = loadKeyStoreFromDisk(keyStoreFile.getAbsolutePath(), password);

		Enumeration<String> aliases = keyStore.aliases();
		if (aliases == null) {
			throw new Exception("No certificate was found.");
		}

		List<CertificateDetails> certificatesDetails = new ArrayList<>();
		while (aliases.hasMoreElements()) {
			String certificateAlias = (String) aliases.nextElement();
			java.security.cert.Certificate certificates[] = keyStore.getCertificateChain(certificateAlias);
			Key key = keyStore.getKey(certificateAlias, password.toCharArray());
			for (java.security.cert.Certificate cert : certificates) {
				CertificateDetails certificateDetails = getCertificateInformation(cert, key, certificateAlias);
				if (certificateDetails != null) {
					certificatesDetails.add(certificateDetails);
				}
			}
		}

		return certificatesDetails;
	}

	/**
	 * 
	 * @param certificate
	 * @param key
	 * @param certificateAlias
	 * @return
	 */
	private CertificateDetails getCertificateInformation(java.security.cert.Certificate certificate, Key key,
			String certificateAlias) {
		if (certificate instanceof X509Certificate) {
			X509Certificate x509Certificate = (X509Certificate) certificate;
			CertificateDetails certificateDetails = new CertificateDetails();
			certificateDetails.setAlias(certificateAlias);
			certificateDetails.setFormat(x509Certificate.getType());
			certificateDetails.setSelfSigned(isSelfSigned(x509Certificate));
			certificateDetails.setSubject(getSubject(x509Certificate));
			certificateDetails.setCommonName(getCommonName(x509Certificate));
			certificateDetails.setIssuer(getIssuer(x509Certificate));
			certificateDetails.setSignatureAlgorithm(x509Certificate.getSigAlgName());
			if (key != null && key instanceof PrivateKey) {
				certificateDetails.setKeySize(getPrivateKeySize((PrivateKey) key));
			}

			try {
				certificateDetails.setPem(getPemValue(x509Certificate));
			} catch (Exception e) {
				// Do nothing.
			}

			Date notBefore = x509Certificate.getNotBefore();
			Date notAfter = x509Certificate.getNotAfter();
			certificateDetails.setNotAfter(df.format(notAfter));
			certificateDetails.setNotBefore(df.format(notBefore));

			boolean expired = true;
			try {
				x509Certificate.checkValidity();
				expired = false;
			} catch (Exception e) {
				// Do nothing.
			}
			certificateDetails.setExpired(expired);

			return certificateDetails;

		}
		return null;
	}

	/**
	 * Method used to retrieve encoded form of a certificate as string.
	 * 
	 * @param xcert
	 *            The certificate from who will extract the encoded form of this
	 *            certificate.
	 * 
	 * @return PEM Certificate as string.
	 * @throws IOException
	 *             If a error is thrown.
	 */
	private String getPemValue(X509Certificate xcert) throws IOException {
		StringWriter stringWriter = null;
		JcaPEMWriter jcaPemWriter = null;
		try {
			stringWriter = new StringWriter();
			jcaPemWriter = new JcaPEMWriter(stringWriter);
			jcaPemWriter.writeObject(xcert);
			jcaPemWriter.flush();
		} finally {
			try {
				if (jcaPemWriter != null) {
					jcaPemWriter.close();
				}
			} catch (Exception e) {
				// Silent.
			}
		}

		return stringWriter.toString();
	}

	/**
	 * Method used to retrieve encoded form of a certificate as string.
	 * 
	 * @param xcert
	 *            The certificate from who will extract the encoded form of this
	 *            certificate.
	 * 
	 * @return PEM Certificate as string.
	 * @throws IOException
	 *             If a error is thrown.
	 */
	private String getPemValuePrivateKey(Key key) throws Exception {
		StringWriter stringWriter = null;
		JcaPEMWriter jcaPemWriter = null;
		try {
			stringWriter = new StringWriter();
			jcaPemWriter = new JcaPEMWriter(stringWriter);
			jcaPemWriter.writeObject(key);
			jcaPemWriter.flush();
		} finally {
			try {
				if (jcaPemWriter != null) {
					jcaPemWriter.close();
				}
			} catch (Exception e) {
				// Silent.
			}
		}

		return stringWriter.toString();
	}

	/**
	 * Check if a certificate is self signed.
	 * 
	 * @param x509Certificate
	 *            Certificate who will be checked if is self signed.
	 * 
	 * @return <b>true</b> if certificate is self signed, <b>false</b>
	 *         otherwise.
	 */
	private boolean isSelfSigned(X509Certificate x509Certificate) {
		Principal issuerDN = x509Certificate.getIssuerDN();
		Principal subjectDN = x509Certificate.getSubjectDN();

		return issuerDN.equals(subjectDN);
	}

	/**
	 * Get issuer for given certificate as string.
	 * 
	 * @param x509Certificate
	 *            The certificate from we extract subject.
	 * @return issuer of given certificate.
	 */
	private String getIssuer(X509Certificate x509Certificate) {
		String issuerDnString = null;

		JcaX509CertificateHolder certHolder;
		try {
			certHolder = new JcaX509CertificateHolder(x509Certificate);
			issuerDnString = certHolder.getIssuer().toString();
		} catch (CertificateEncodingException e) {
			// Silent.
		}

		if (issuerDnString == null) {
			Map<String, String> additionalOIDs = new HashMap<String, String>();
			additionalOIDs.put(BCStyle.E.getId(), "E");
			issuerDnString = x509Certificate.getIssuerX500Principal().getName(X500Principal.RFC2253, additionalOIDs);
		}

		return issuerDnString;
	}

	/**
	 * Get subject for given certificate as string.
	 * 
	 * @param x509Certificate
	 *            The certificate from we extract subject.
	 * @return subject of given certificate.
	 */
	private String getSubject(X509Certificate x509Certificate) {
		String subjectDnString = null;

		JcaX509CertificateHolder certHolder;
		try {
			certHolder = new JcaX509CertificateHolder(x509Certificate);
			subjectDnString = certHolder.getSubject().toString();
		} catch (CertificateEncodingException e) {
			// Silent.
		}

		if (subjectDnString == null) {
			Map<String, String> additionalOIDs = new HashMap<String, String>();
			additionalOIDs.put(BCStyle.E.getId(), "E");
			subjectDnString = x509Certificate.getSubjectX500Principal().getName(X500Principal.RFC2253, additionalOIDs);
		}

		return subjectDnString;
	}

	/**
	 * Load a KeyStore from disk based on full path and password.
	 * 
	 * @param keyStoreFullPath
	 *            Full path to KeyStore.
	 * @param keyStorePassword
	 *            Password for KeyStore.
	 * 
	 * @return KeyStore object who represents the file from disk.
	 * 
	 * @throws Exception
	 *             If a error occurs.
	 */
	private KeyStore loadKeyStoreFromDisk(String keyStoreFullPath, String keyStorePassword) throws Exception {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(keyStoreFullPath);
			return loadKeyStore(inputStream, keyStorePassword);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				// Stay silent.
			}
		}
	}

	/**
	 * Load a KeyStore from disk based on full path and password.
	 * 
	 * @param keyStoreFullPath
	 *            Full path to KeyStore.
	 * @param keyStorePassword
	 *            Password for KeyStore.
	 * 
	 * @return KeyStore object who represents the file from disk.
	 * 
	 * @throws Exception
	 *             If a error occurs when we try to load keyStore.
	 */
	private KeyStore loadKeyStore(InputStream inputStream, String keyStorePassword) throws Exception {
		KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
		keyStore.load(inputStream, keyStorePassword.toCharArray());

		return keyStore;
	}

	/**
	 * This method gets a key and computes the size of the key. The key can have
	 * more formats as is an interface for multiple key types.
	 * <p/>
	 * This method handles: RSAKey, DSAKey and DHKey.
	 *
	 * @param key
	 *            The public key for which the size needs to be calculated.
	 * @return An integer representing the size of the key.
	 */
	private int getPrivateKeySize(final PrivateKey key) {
		int size = 0;
		if (key instanceof RSAKey) {
			BigInteger modulus = ((RSAKey) key).getModulus();
			size = modulus.bitLength();
		} else if (key instanceof DSAKey) {
			DSAParams parameters = ((DSAKey) key).getParams();
			size = parameters.getP().bitLength();
		} else if (key instanceof DHKey) {
			DHParameterSpec parameters = ((DHKey) key).getParams();
			size = parameters.getP().bitLength();
		} else if (key instanceof ECKey) {
			ECPrivateKey ec = (ECPrivateKey) key;
			size = ec.getS().bitLength();
		}

		return size;
	}

	/**
	 * Generate CSR based on certificate and return generated CSR as string.
	 * 
	 * @param certificate
	 *            A certificate on the basis of which it is generated the CSR.
	 * @param key
	 *            Private key from keyStore used to get information such as
	 *            algorithm used and key size.
	 * 
	 * @return generated CSR as string.
	 * 
	 * @throws Exception
	 *             If a error occurs.
	 */
	private InputStream generateCSR(java.security.cert.Certificate certificate, Key key) throws Exception {
		JcaPEMWriter jcaPEMWriter = null;
		StringWriter stringWriter = null;
		try {
			if (certificate instanceof X509Certificate) {
				X509Certificate cert = (X509Certificate) certificate;
				PublicKey publicKey = cert.getPublicKey();

				JcaX509CertificateHolder certificateHolder = new JcaX509CertificateHolder(cert);
				X500Name subjectX500Name = certificateHolder.getSubject();
				if (subjectX500Name == null) {
					throw new CertificateEncodingException("Certificate subject can not be obtained.");
				}

				ContentSigner contentSigner = new JcaContentSignerBuilder(cert.getSigAlgName()).build((PrivateKey) key);
				PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(subjectX500Name,
						publicKey);
				PKCS10CertificationRequest csr = builder.build(contentSigner);

				stringWriter = new StringWriter();
				jcaPEMWriter = new JcaPEMWriter(stringWriter);
				jcaPEMWriter.writeObject(csr);
				jcaPEMWriter.flush();

				return new ByteArrayInputStream(stringWriter.toString().getBytes());
			} else {
				throw new Exception("This format of certificate is not supported.");
			}
		} finally {
			try {
				if (jcaPEMWriter != null) {
					jcaPEMWriter.close();
				}
			} catch (Exception e) {
				// Silent.
			}
		}
	}

	/**
	 * 
	 * @param findedKeystore
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public InputStream generateCSR(Keystore findedKeystore, User user) throws Exception {
		String password = AES.decrypt(findedKeystore.getPassword());

		File keyStoreFile = getKeyStoreFile(user.getId(), findedKeystore.getName());

		KeyStore keyStore = loadKeyStoreFromDisk(keyStoreFile.getAbsolutePath(), password);

		Enumeration<String> aliases = keyStore.aliases();
		if (aliases == null) {
			throw new Exception("No certificate was found.");
		}
		String certificateAlias = aliases.nextElement();
		if (certificateAlias == null) {
			throw new Exception("No certificate was found.");
		}

		java.security.cert.Certificate certificate = keyStore.getCertificate(certificateAlias);
		Key key = keyStore.getKey(certificateAlias, password.toCharArray());

		return generateCSR(certificate, key);
	}

	/**
	 * 
	 * @param userId
	 * @param keyStoreFileName
	 * @return
	 * @throws Exception
	 */
	public File getKeyStoreFile(String userId, String keyStoreFileName) throws Exception {
		File userDirectory = new File(keystoreFolder, userId);
		if (!userDirectory.exists() && !userDirectory.isDirectory()) {
			throw new Exception("No certificate was found.");
		}

		File keyStoreFile = new File(userDirectory, keyStoreFileName);
		if (!keyStoreFile.exists()) {
			throw new Exception("No certificate was found.");
		}

		return keyStoreFile;
	}

	/**
	 * 
	 * @param findedKeystore
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public InputStream export_certificate(Keystore findedKeystore, User user) throws Exception {
		String password = AES.decrypt(findedKeystore.getPassword());

		File keyStoreFile = getKeyStoreFile(user.getId(), findedKeystore.getName());

		KeyStore keyStore = loadKeyStoreFromDisk(keyStoreFile.getAbsolutePath(), password);

		Enumeration<String> aliases = keyStore.aliases();
		if (aliases == null) {
			throw new Exception("No certificate was found.");
		}
		String certificateAlias = aliases.nextElement();
		if (certificateAlias == null) {
			throw new Exception("No certificate was found.");
		}

		java.security.cert.Certificate certificate = keyStore.getCertificate(certificateAlias);
		return new ByteArrayInputStream(getPemValue((X509Certificate) certificate).getBytes());
	}

	/**
	 * Export private key in PEM format.
	 * 
	 * @param findedKeystore
	 *            Find keyStore from database.
	 * @param user
	 *            The authenticated user.
	 * @return a stream with private key in PEM format.
	 * @throws Exception
	 *             If a error occurs.
	 */
	public InputStream export_privateKey(Keystore findedKeystore, User user) throws Exception {
		String password = AES.decrypt(findedKeystore.getPassword());

		File keyStoreFile = getKeyStoreFile(user.getId(), findedKeystore.getName());

		KeyStore keyStore = loadKeyStoreFromDisk(keyStoreFile.getAbsolutePath(), password);

		Enumeration<String> aliases = keyStore.aliases();
		if (aliases == null) {
			throw new Exception("No certificate was found.");
		}
		String certificateAlias = aliases.nextElement();
		if (certificateAlias == null) {
			throw new Exception("No certificate was found.");
		}

		Key key = keyStore.getKey(certificateAlias, password.toCharArray());

		return new ByteArrayInputStream(key.getEncoded());
	}

	/**
	 * Upload a certificate using files.
	 * 
	 * @param certificate
	 * @param privateKey
	 * @param user
	 * @throws Exception
	 */
	public void uploadCertificate(MultipartFile certificate, MultipartFile privateKey, User user) throws Exception {
		if (certificate.isEmpty() && privateKey.isEmpty()) {
			throw new Exception("You must provide both files.");
		}

		importCertificate(null, null, certificate.getBytes(), privateKey.getBytes(), user);
	}

	public void deleteCertificate(User user, String id) {

	}

	/**
	 * Convert private key text to private key object.
	 * 
	 * @param privateKeyText
	 *            The private key who will be converted.
	 * @return obtained private key object.
	 * @throws Exception
	 *             If a error occurs.
	 */
	private PrivateKey textToPrivateKey(String privateKeyText) throws Exception {
		PEMParser parser = null;
		try {
			parser = new PEMParser(new StringReader(privateKeyText));
			Object o = parser.readObject();
			if (o == null || !(o instanceof PEMKeyPair)) {
				throw new IOException("The private key is invalid.");
			}
			PEMKeyPair pemKeyPair = (PEMKeyPair) o;
			PrivateKey privateKey = new JcaPEMKeyConverter().getPrivateKey(pemKeyPair.getPrivateKeyInfo());

			return privateKey;

		} finally {
			try {
				if (parser != null) {
					parser.close();
				}
			} catch (Exception e) {
				// Do nothing.
			}
		}
	}

	/**
	 * Sign a document using provided private key.
	 * 
	 * @param documentToSign
	 *            The document who will be signed.
	 * @param privateKeyFile
	 *            The key used to sign document.
	 * @return Signed document.
	 * @throws Exception
	 */
	public InputStream signDocument(MultipartFile documentToSign, MultipartFile privateKeyFile) throws Exception {
		if (documentToSign.isEmpty() && privateKeyFile.isEmpty()) {
			throw new Exception("You must provide both files.");
		}

		return new ByteArrayInputStream(
				signData(documentToSign.getBytes(), textToPrivateKey(new String(privateKeyFile.getBytes()))));
	}

	/**
	 * Sign data.
	 * 
	 * @param bytes
	 *            The data who will be signed.
	 * @param privateKey
	 * @return signed data.
	 * @throws Exception
	 *             If a error occurs.
	 */
	private byte[] signData(byte[] bytes, PrivateKey privateKey) throws Exception {
		String algorithm = privateKey.getAlgorithm();
		if (algorithm.equals("RSA")) {
			algorithm = "SHA256WithRSA";
		} else {
			algorithm = "SHA256WithECDSA";
		}
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(privateKey);
		signature.update(bytes);

		return signature.sign();
	}

	/**
	 * Sign provided text using provided private key.
	 * 
	 * @param textToSign
	 *            The text who will be signed.
	 * @param privateKey
	 *            Key used for signing.
	 * @return signed text.
	 * @throws Exception
	 *             If a error occurs.
	 */
	public InputStream signText(String textToSign, MultipartFile privateKey) throws Exception {
		if (privateKey.isEmpty()) {
			throw new Exception("You must provide both files.");
		}

		return new ByteArrayInputStream(
				signData(textToSign.getBytes(), textToPrivateKey(new String(privateKey.getBytes()))));
	}

	/**
	 * Retrieve certificates and save in a new keystore.
	 * 
	 * @param aURL
	 *            The URL from certificates will be retrieved.
	 * @throws Exception
	 *             If a error occurs.
	 */
	public List<CertificateDetails> retrieveCertificates(URL destinationURL) throws Exception {
		HttpsURLConnection conn = (HttpsURLConnection) destinationURL.openConnection();
		conn.connect();
		java.security.cert.Certificate[] certs = conn.getServerCertificates();
		List<CertificateDetails> certificatesDetails = new ArrayList<>();
		for (java.security.cert.Certificate cert : certs) {
			CertificateDetails certificateDetails = getCertificateInformation(cert, null, destinationURL.getHost());
			if (certificateDetails != null) {
				certificatesDetails.add(certificateDetails);
			}
		}

		return certificatesDetails;
	}
}
