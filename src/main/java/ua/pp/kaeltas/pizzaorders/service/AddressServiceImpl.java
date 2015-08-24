package ua.pp.kaeltas.pizzaorders.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.AddressServiceI#find(ua.pp.kaeltas.pizzaorders.domain.Address)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.AddressServiceI#save(ua.pp.kaeltas.pizzaorders.domain.Address)
	 */
	@Override
	public void save(Address address) {
		addressRepository.save(address);
	}
	
}
