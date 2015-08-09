package ua.pp.kaeltas.pizzaorders.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.infrastructure.Benchmark;

//@Repository
public class TestOrderRepository /*implements OrderRepository*/ {

	private List<Order> orders = new LinkedList<>();
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.repository.OrderRepository#saveOrder(ua.pp.kaeltas.pizzaorders.domain.Order)
	 */
	//@Override
	@Benchmark
	public void saveOrder(Order newOrder) {
		newOrder.setId(orders.size());
		
		orders.add(newOrder);
	}

	//@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
}
