package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.repository.PreviousHighSchoolRepository;

@Service
@Transactional
public class PreviousHighSchoolService {

	@Autowired
	private PreviousHighSchoolRepository previousHighSchoolRepository;
}
