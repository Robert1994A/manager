package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.LegalParentFather;
import ro.inf.ucv.admitere.repository.LegalParentFatherRepository;

@Service
@Transactional
public class LegalParentFatherService {

	@Autowired
	private LegalParentFatherRepository legalParentFatherRepository;

	public LegalParentFather save(LegalParentFather legalParentFather) {
		return legalParentFatherRepository.save(legalParentFather);
	}
}
