package ua.pp.kaeltas.pizzaorders.domain;

import java.util.Map;
import java.util.Map.Entry;

public class TotalOrderPriceCalculator {

	private static final float DISCHARGE_PERCENT_FOR_HIGHEST_PRICE_PIZZA = 50f/100f;
	private static final int PIZZA_COUNT_TO_GET_DISCHARGE = 4;
	private static final int MIN_COUNT_OF_PIZZAS_IN_ORDER = 1;
	private static final int MAX_COUNT_OF_PIZZAS_IN_ORDER = 10;

	/**
	 * Constraints: order contains 1-10 pizzas, else - exception
	 * If number of pizzas in order more than 4, then give discharge 50% to the highest price pizza 
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
			orderPrice += pizza.getPrice() * pizzaCount;
		}
		
		return orderPrice - calculatePizzaDischarge(pizzas);
	}
	

	int calculatePizzaDischarge(Map<Pizza, Integer> pizzas) {
		int maxPizzaPrice = 0;
		if (calcTotalPizzasCount(pizzas) > PIZZA_COUNT_TO_GET_DISCHARGE) {
			for(Entry<Pizza, Integer> entry : pizzas.entrySet()) {
				Pizza pizza = entry.getKey();
				maxPizzaPrice = Math.max(maxPizzaPrice, pizza.getPrice());
			}
		}
		
		return (int)(maxPizzaPrice*DISCHARGE_PERCENT_FOR_HIGHEST_PRICE_PIZZA);
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
				|| calcTotalPizzasCount(pizzas) > MAX_COUNT_OF_PIZZAS_IN_ORDER 
				|| calcTotalPizzasCount(pizzas) < MIN_COUNT_OF_PIZZAS_IN_ORDER) {
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
