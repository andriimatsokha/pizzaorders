package ua.pp.kaeltas.pizzaorders.repository;

import java.util.LinkedList;
import java.util.List;

import ua.pp.kaeltas.pizzaorders.domain.Order;

public class TestOrderRepository implements OrderRepository {

	private List<Order> orders = new LinkedList<>();
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.repository.OrderRepository#saveOrder(ua.pp.kaeltas.pizzaorders.domain.Order)
	 */
	@Override
	public void saveOrder(Order newOrder) {
		newOrder.setId(orders.size());
		
		orders.add(newOrder);
	}
}
