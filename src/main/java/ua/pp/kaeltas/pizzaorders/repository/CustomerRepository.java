package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.Customer;

public interface CustomerRepository {
	
	Customer find(Integer id);
	void save(Customer customer);

}
