package ro.inf.ucv.admitere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.inf.ucv.admitere.entity.UserPersonalData;
import ro.inf.ucv.admitere.repository.UserPersonalDataRepository;

@Transactional
@Service
public class UserPersonalDataService {

	@Autowired
	private UserPersonalDataRepository userPersonalDataRepository;

	public UserPersonalData save(UserPersonalData userPersonalData) {
		return userPersonalDataRepository.save(userPersonalData);
	}
}
