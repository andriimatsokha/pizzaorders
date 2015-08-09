package ua.pp.kaeltas.pizzaorders.service;

import java.util.List;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;

public interface OrderService {

	public abstract Order placeNewOrder(Customer customer, Address address, Integer... pizzasID);
	public abstract List<Order> getAllOrders();

	
}