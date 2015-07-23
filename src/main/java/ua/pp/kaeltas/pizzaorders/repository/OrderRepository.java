package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.Order;

public interface OrderRepository {

	public abstract void saveOrder(Order newOrder);

}