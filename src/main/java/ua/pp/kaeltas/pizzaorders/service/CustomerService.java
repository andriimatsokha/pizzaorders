package ua.pp.kaeltas.pizzaorders.service;

import ua.pp.kaeltas.pizzaorders.domain.Customer;

public interface CustomerService {

	public abstract Customer getByName(String name);

	public abstract void update(Customer customer);

}