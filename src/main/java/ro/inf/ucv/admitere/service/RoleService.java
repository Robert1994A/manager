package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.exceptions.UserAlreadyExist;
import ro.inf.ucv.admitere.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void save(Role role) {
        if (roleRepository.countByName(role.getName()) != 0) {
            throw new UserAlreadyExist("Role with name " + role.getName() + " already exist!");
        }
        roleRepository.save(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
