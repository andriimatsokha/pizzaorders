package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.Address;

public interface AddressRepository {

	public abstract void saveAddress(Address address);
	
}
