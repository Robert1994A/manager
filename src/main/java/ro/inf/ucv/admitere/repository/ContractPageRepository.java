package ro.inf.ucv.admitere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Contract;

@Repository
public interface ContractPageRepository extends JpaRepository<Contract, Long> {

	Page<Contract> findAll(Pageable pageable);

}
