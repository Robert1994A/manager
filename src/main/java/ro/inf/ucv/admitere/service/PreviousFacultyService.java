package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.repository.PreviousFacultyRepository;

@Service
@Transactional
public class PreviousFacultyService {

	@Autowired
	private PreviousFacultyRepository previousFacultyRepository;
}
