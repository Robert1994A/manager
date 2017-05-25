package ro.certificate.manager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.certificate.manager.entity.User;
import ro.certificate.manager.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> findAll() throws Exception {
		List<User> allUsers = userRepository.findAll();
		if (allUsers == null) {
			throw new Exception("Any user not found in databse!");
		}
		return allUsers;
	}

	public User findOne(String id) throws Exception {
		if (id == null) {
			throw new Exception("User with id " + id + " was not found!");
		}
		User userFromDatabase = userRepository.findOne(id);
		if (userFromDatabase == null) {
			throw new Exception("User with id " + id + " was not found!");
		}
		return userRepository.findOne(id);
	}

	public User findByUsername(String username) throws Exception {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new Exception("User with username " + username + " was not found!");
		}
		return user;
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Long count() {
		return userRepository.count();
	}

	public Page<User> searchUser(String userSearchValue, PageRequest pageRequest) {
		Page<User> searchedUsers = userRepository.findByUsernameIgnoreCaseContaining(userSearchValue, pageRequest);
		return searchedUsers;
	}

	public Long countUserWithSearch(String userSearchValue) {
		return userRepository.countFindByUsernameIgnoreCaseContaining(userSearchValue);
	}

	public User findByRegisterToken(String registerToken) {
		return userRepository.findByRegisterToken(registerToken);
	}

	public void deleteById(String id) {
		userRepository.delete(id);
	}

	public User findByUsernameOrEmail(String username, String email) {
		return userRepository.findByUsernameOrEmail(username, email);
	}

	public User findByRecoverPaswordToken(String recoverToken) {
		return userRepository.findByRecoverPaswordToken(recoverToken);
	}

	public List<User> searchByUsernameOrEmail(String username) {
		return userRepository.findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContaining(username, username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Page<User> findByUsernameOrEmailIgnoreCase(String query, Pageable pageRequest) {
		return userRepository.findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContaining(query, query, pageRequest);
	}

	public User findByRecoverPaswordTokenAndEmail(String recoverToken, String email) {
		return userRepository.findByRecoverPaswordTokenAndEmail(recoverToken, email);
	}

	public User findByRegisterTokenAndEmail(String registerToken, String email) {
		return userRepository.findByRegisterTokenAndEmail(registerToken, email);
	}
}
