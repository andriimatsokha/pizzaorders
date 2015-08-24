package ua.pp.kaeltas.pizzaorders.service;

import java.util.Map;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;

public interface TotalOrderPriceService {

	public abstract int calculateTotalOrderPrice(Map<Pizza, Integer> pizzas,
			int accumulativeCardSum);

	public abstract int calculateTotalOrderPriceWithoutDiscount(
			Map<Pizza, Integer> pizzas, int accumulativeCardSum);

	public abstract int calculateOrderDiscount(Map<Pizza, Integer> pizzas,
			int accumulativeCardSum);

}