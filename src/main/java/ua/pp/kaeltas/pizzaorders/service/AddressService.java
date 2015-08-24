package ua.pp.kaeltas.pizzaorders.service;

import ua.pp.kaeltas.pizzaorders.domain.Address;

public interface AddressService {

	public abstract Address find(Address address);

	public abstract void save(Address address);

}