package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.Customer;

public interface CustomerRepository {
	
	Customer find(Integer id);
	void save(Customer customer);
	Customer findByName(String name);
	void update(Customer customer);

}
