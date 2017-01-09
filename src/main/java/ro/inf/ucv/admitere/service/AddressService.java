package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.repository.AddressRepository;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
}
