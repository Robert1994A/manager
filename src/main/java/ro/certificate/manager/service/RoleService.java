package ro.certificate.manager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.certificate.manager.entity.Role;
import ro.certificate.manager.repository.RoleRepository;

/**
 * This class is used to interrogate database to access user roles.
 * 
 * @author Robert
 *
 */
@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role save(Role role) {
		if (roleRepository.countByName(role.getName()) != 0) {
			return null;
		}
		return roleRepository.save(role);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
