package ua.pp.kaeltas.pizzaorders.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;

@Repository
public class JPAOrderRepository implements OrderRepository {
	
	private static final String SELECT_FROM_ORDER_BY_CUSTOMER = "SELECT o FROM Order o WHERE o.customer.id = :customerId";
	private static final String SELECT_ALL_FROM_ORDER = "SELECT o FROM Order o";
	@PersistenceContext(unitName="HibernatePostgreSQL")
	EntityManager em;

	@Override
	@Transactional
	public void saveOrder(Order order) {
		em.persist(order);
	}

	@Override
	public List<Order> getAllOrders() {
		
		TypedQuery<Order> query = em.createQuery(SELECT_ALL_FROM_ORDER, Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> findByCustomer(Customer customer) {
		
		TypedQuery<Order> query = em.createQuery(SELECT_FROM_ORDER_BY_CUSTOMER, Order.class);
		query.setParameter("customerId", customer.getId());
		
		return query.getResultList();
	}
	
	

}
