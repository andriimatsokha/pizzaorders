package ua.pp.kaeltas.pizzaorders.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	public Address find(Address address) {
		Address ormAddress = null;
		try {
			ormAddress = addressRepository.find(address.getStreet(), address.getHouseNumber());
		} catch (NoResultException ex) {
			addressRepository.save(address);
			ormAddress = address;
		}
		
		return ormAddress;
	}

	public void save(Address address) {
		addressRepository.save(address);
	}
	
}
