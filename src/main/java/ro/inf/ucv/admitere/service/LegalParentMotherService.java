package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.LegalParentMother;
import ro.inf.ucv.admitere.repository.LegalParentMotherRepository;

@Service 
@Transactional
public class LegalParentMotherService {

	@Autowired
	private LegalParentMotherRepository legalParentMotherRepository;

	public LegalParentMother save(LegalParentMother legalParentMother) {
		return legalParentMotherRepository.save(legalParentMother);
	}
}
