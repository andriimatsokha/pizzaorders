package ua.pp.kaeltas.pizzaorders.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Customer;

@Repository
public class JPACustomerRepository implements CustomerRepository {

	private static final String SELECT_FROM_CUSTOMER_BY_NAME = "SELECT c FROM Customer c WHERE c.name=:name";
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

	@Override
	public Customer findByName(String name) {
		
		TypedQuery<Customer> query = em.createQuery(SELECT_FROM_CUSTOMER_BY_NAME, Customer.class);
		query.setParameter("name", name);
		
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public void update(Customer customer) {
		em.merge(customer);
	}

}
