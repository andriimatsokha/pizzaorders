package ua.pp.kaeltas.pizzaorders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer getByName(String name) {
		return customerRepository.findByName(name);
	}

	public void update(Customer customer) {
		customerRepository.update(customer);
	}
	
}
