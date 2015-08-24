package ua.pp.kaeltas.pizzaorders.service;

import java.util.List;
import java.util.Map;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;

public interface OrderService {

	public abstract Order placeNewOrder(Customer customer, Address address, Integer... pizzasID);
	public abstract Order placeNewOrder(Customer customer, Address address, Map<Pizza, Integer> pizzas);
	public abstract List<Order> getAllOrders();
	
}