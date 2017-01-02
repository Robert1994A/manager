package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	int countByName(String name);

	Role findByName(String name);

}
