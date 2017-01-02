package ro.inf.ucv.admitere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String name);

	int countByUsername(String username);

	Page<User> findByUsernameIgnoreCaseContaining(String userSearchValue, Pageable pageRequest);

	Long countFindByUsernameIgnoreCaseContaining(String userSearchValue);

	User findByRegisterToken(String registerToken);

	Object findByEmail(String email);

	User findByUsernameOrEmail(String username, String email);

	User findByRecoverPaswordToken(String recoverToken);

}
