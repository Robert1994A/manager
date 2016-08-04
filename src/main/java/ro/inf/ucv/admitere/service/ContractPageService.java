package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.ContractPage;
import ro.inf.ucv.admitere.repository.ContractPageRepository;

@Service
@Transactional
public class ContractPageService {

	@Autowired
	private ContractPageRepository contractPageRepository;

	public ContractPage findOne(Long id) {
		return contractPageRepository.findOne(id);
	}

	public void save(ContractPage contractPage) {
		contractPageRepository.save(contractPage);
	}

	public List<ContractPage> findAll() {
		return contractPageRepository.findAll();
	}

	public Long count() {
		return contractPageRepository.count();
	}

	public void updateContractPage(ContractPage contractPage) {
		contractPageRepository.save(contractPage);
	}
}
