package ua.pp.kaeltas.pizzaorders.domain;

import java.util.Map;
import java.util.Map.Entry;

public class TotalOrderPriceCalculator {

	/**
	 * Constraints: order contains 1-10 pizzas, else - exception
	 * If number of pizzas in order more than 4, then give discharge to the highest price pizza 
	 * 
	 * @param pizzas
	 * @return sum of order
	 */
	public int calculateTotalOrderPrice(Map<Pizza, Integer> pizzas) {
		
		validatePizzas(pizzas);
		
		int orderPrice = 0;
		for(Entry<Pizza, Integer> entry : pizzas.entrySet()) {
			Pizza pizza = entry.getKey();
			Integer pizzaCount = entry.getValue();
			orderPrice += entry.getKey().getPrice() * entry.getValue();
		}
		
		return orderPrice;
	}
	
	
	private int calcTotalPizzasCount(Map<Pizza, Integer> pizzas) {
		int totalCount = 0;
		for (Integer countOfPizzas : pizzas.values()) {
			totalCount += countOfPizzas; 
		}
		
		return totalCount;
	}
	
	private void validatePizzas(Map<Pizza, Integer> pizzas) {
		if (pizzas.isEmpty() 
				|| calcTotalPizzasCount(pizzas) > 10 
				|| calcTotalPizzasCount(pizzas) < 1) {
			throw new IllegalArgumentException("No pizzas in order");
		}
		
		for(Entry<Pizza, Integer> entry : pizzas.entrySet()) {
			Pizza pizza = entry.getKey();
			Integer pizzaCount = entry.getValue();
			if (pizza.getPrice() < 0 || pizzaCount < 0) {
				throw new IllegalArgumentException("Negative pizza price or count");
			}
		}
	}
	
}
