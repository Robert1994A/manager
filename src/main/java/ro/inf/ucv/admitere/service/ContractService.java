package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Contract;
import ro.inf.ucv.admitere.repository.ContractRepository;

@Service
@Transactional
public class ContractService {

	@Autowired
	private ContractRepository contractPageRepository;

	public Contract findOne(Long id) {
		return contractPageRepository.findOne(id);
	}

	public void save(Contract contractPage) {
		contractPageRepository.save(contractPage);
	}

	public List<Contract> findAll() {
		return contractPageRepository.findAll();
	}

	public Long count() {
		return contractPageRepository.count();
	}

	public void updateContractPage(Contract contractPage) {
		contractPageRepository.save(contractPage);
	}

	public Page<Contract> findAll(Pageable pageable) {
		return contractPageRepository.findAll(pageable);
	}
}
