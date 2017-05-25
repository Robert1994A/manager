package manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;

public class TestSecuredConnection {
	public static void main(String args[]) throws Exception {
		PdfStamper stp = null;
		PdfSignatureAppearance sap = null;
		PdfReader reader = null;
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream("C:\\Users\\Robert\\Desktop\\keystore m\\astro_ec_384.keystore"),
					"changeit".toCharArray());

			String alias = (String) ks.aliases().nextElement();
			java.security.cert.Certificate[] chain = ks.getCertificateChain(alias);
			reader = new PdfReader("C:\\Users\\Robert\\Desktop\\orar_master_semestrul2_2017.pdf");
			FileOutputStream fout = new FileOutputStream("C:\\Users\\Robert\\Desktop\\signed.pdf");
			stp = PdfStamper.createSignature(reader, fout, '\0');
			sap = stp.getSignatureAppearance();
			sap.setCertificate(chain[0]);
			sap.setReason("I'm the author");
			sap.setLocation("Lisbon");
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
}
