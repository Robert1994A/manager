package ro.certificate.manager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.certificate.manager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String name);

	int countByUsername(String username);

	Page<User> findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContaining(String username, String email,
			Pageable pageRequest);

	Page<User> findByUsernameIgnoreCaseContaining(String userSearchValue, Pageable pageRequest);

	Long countFindByUsernameIgnoreCaseContaining(String userSearchValue);

	User findByRegisterToken(String registerToken);

	User findByEmail(String email);

	User findByUsernameOrEmail(String username, String email);

	User findByRecoverPaswordToken(String recoverToken);

	List<User> findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContaining(String username, String username2);

	Page<User> findByUsernameOrEmailIgnoreCaseContaining(String query, String query1, Pageable pageRequest);

	Page<User> findByUsernameIgnoreCaseOrEmailIgnoreCaseContaining(String query, String query2, Pageable pageRequest);

	User findByRecoverPaswordTokenAndEmail(String recoverToken, String email);

	User findByRegisterTokenAndEmail(String registerToken, String email);
}
