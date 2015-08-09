package ua.pp.kaeltas.pizzaorders.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Order;

@Repository
public class JPAOrderRepository implements OrderRepository {
	
	@PersistenceContext(unitName="HibernatePostgreSQL")
	EntityManager em;

	@Override
	@Transactional
	public void saveOrder(Order order) {
		em.persist(order);
	}

	@Override
	public List<Order> getAllOrders() {
		
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
		return query.getResultList();
	}
	
	

}
