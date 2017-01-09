package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.inf.ucv.admitere.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByName(String countryName);

	List<Country> findByNameContainingIgnoreCase(String countryName);

	Country findByCodeContainingIgnoreCase(String code);

	List<Country> findByNameOrCodeContainingIgnoreCase(String countryName, String code);

}
