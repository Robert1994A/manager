package ro.certificate.manager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.certificate.manager.entity.Keystore;
import ro.certificate.manager.entity.User;
import ro.certificate.manager.repository.KeystoreRepository;

/**
 * This class is used to interrogate database to access user roles.
 * 
 * @author Robert
 *
 */
@Service
@Transactional
public class KeystoreService {

	@Autowired
	KeystoreRepository keystoreRepository;

	public List<Keystore> findAll() {
		return keystoreRepository.findAll();
	}

	public Keystore save(Keystore keystore) {
		return keystoreRepository.save(keystore);
	}

	public List<Keystore> findByUser(User user) {
		return keystoreRepository.findByUser(user);
	}

	public List<Keystore> findByUserAndCertificateSubjectContainingIgnoreCase(User user, String query) {
		return keystoreRepository.findByUserAndCertificateSubjectContainingIgnoreCase(user, query);
	}
}
