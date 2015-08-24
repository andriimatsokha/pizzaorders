package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.Address;

public interface AddressRepository {

	public abstract void save(Address address);

	public abstract Address find(int id);

	public abstract Address find(String street, String houseNumber);
	
}
