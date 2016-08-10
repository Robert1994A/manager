package ro.inf.ucv.admitere.service;

//import javax.transaction.Transactional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserAlreadyExist;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void save(User user) {
		if (userRepository.countByUsername(user.getUsername()) != 0) {
			throw new UserAlreadyExist("User with username " + user.getUsername() + " already exist!");
		}
		userRepository.save(user);
	}

	public List<User> findAll() throws UserNotFound {
		List<User> allUsers = userRepository.findAll();
		if (allUsers == null) {
			throw new UserNotFound("Any user not found in databse!");
		}
		return allUsers;
	}

	public User findOne(Long id) throws UserNotFound {
		if (id == null) {
			throw new UserNotFound("User with id " + id + " was not found!");
		}
		User userFromDatabase = userRepository.findOne(id);
		if (userFromDatabase == null) {
			throw new UserNotFound("User with id " + id + " was not found!");
		}
		return userRepository.findOne(id);
	}

	public User findByUsername(String username) throws UserNotFound {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotFound("User with username " + username + " was not found!");
		}
		return user;
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Long count() {
		return userRepository.count();
	}
}
