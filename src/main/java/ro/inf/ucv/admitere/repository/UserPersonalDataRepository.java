package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.UserPersonalData;

@Repository
public interface UserPersonalDataRepository extends JpaRepository<UserPersonalData, Long> {

	UserPersonalData findByCnp(String cnp);

	UserPersonalData findByPhoneNumber(String phoneNumber);

}
