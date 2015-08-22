package ua.pp.kaeltas.pizzaorders.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Customer;

@Repository
public class JPACustomerRepository implements CustomerRepository {

	@PersistenceContext(unitName="HibernatePostgreSQL")
	EntityManager em;
	
	@Override
	@Transactional
	public void save(Customer customer) {
		em.persist(customer);
	}

	@Override
	public Customer find(Integer id) {
		return em.find(Customer.class, id);
	}

}
