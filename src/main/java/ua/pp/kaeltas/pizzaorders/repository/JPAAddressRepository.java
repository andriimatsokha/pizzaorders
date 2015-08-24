package ua.pp.kaeltas.pizzaorders.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Address;

@Repository
public class JPAAddressRepository implements AddressRepository {

	private static final String SQL_SELECT_ADDRESS_BY_STREET_AND_HOUSENUMBER = 
					"SELECT a FROM Address a "
					+ "WHERE a.street = :street "
					+ "AND a.houseNumber = :houseNumber";
	
	
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

	@Override
	public Address find(String street, String houseNumber) {
		
		TypedQuery<Address> query = em.createQuery(
				SQL_SELECT_ADDRESS_BY_STREET_AND_HOUSENUMBER, Address.class);
		query.setParameter("street", street);
		query.setParameter("houseNumber", houseNumber);
		
		return query.getSingleResult();
	}

}
