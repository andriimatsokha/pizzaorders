package ua.pp.kaeltas.pizzaorders.repository;

import java.util.List;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;

public interface OrderRepository {

	public abstract void saveOrder(Order newOrder);
	public abstract List<Order> getAllOrders();
	public abstract List<Order> findByCustomer(Customer customer);

}