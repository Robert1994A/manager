package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.repository.CountryRepository;

@Service
@Transactional
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public void save(Country country) {
		countryRepository.save(country);
	}

	public Country findByName(String countryName) {
		return countryRepository.findByName(countryName);
	}

	public List<Country> findByNameContaining(String countryName) {
		return countryRepository.findByNameContainingIgnoreCase(countryName);
	}

	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public Country findByCode(String code) {
		return countryRepository.findByCodeContainingIgnoreCase(code);
	}

	public List<Country> findByNameOrCodeContaining(String countryName, String code) {
		return countryRepository.findByNameOrCodeContainingIgnoreCase(countryName, code);
	}
}
