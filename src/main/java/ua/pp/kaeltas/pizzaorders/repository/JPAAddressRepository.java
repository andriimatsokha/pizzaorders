package ua.pp.kaeltas.pizzaorders.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Address;

@Repository
public class JPAAddressRepository implements AddressRepository {

	@PersistenceContext(unitName="HibernatePostgreSQL")
	EntityManager em;
	
	@Override
	@Transactional
	public void save(Address address) {
		em.persist(address);
	}

	@Override
	public Address find(int id) {
		return em.find(Address.class, id);
	}

}
