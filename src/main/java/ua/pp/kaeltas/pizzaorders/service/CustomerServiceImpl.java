package ua.pp.kaeltas.pizzaorders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.CustomerServiceI#getByName(java.lang.String)
	 */
	@Override
	public Customer getByName(String name) {
		return customerRepository.findByName(name);
	}

	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.CustomerServiceI#update(ua.pp.kaeltas.pizzaorders.domain.Customer)
	 */
	@Override
	public void update(Customer customer) {
		customerRepository.update(customer);
	}
	
}
