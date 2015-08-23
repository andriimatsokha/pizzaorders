package ua.pp.kaeltas.pizzaorders.domain;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TotalOrderPriceCalculator {

	private static final float DISCOUNT_PERCENT_FOR_HIGHEST_PRICE_PIZZA = 50f/100f;
	private static final int PIZZA_COUNT_TO_GET_DISCOUNT = 4;
	private static final int MIN_COUNT_OF_PIZZAS_IN_ORDER = 1;
	private static final int MAX_COUNT_OF_PIZZAS_IN_ORDER = 10;

	@Autowired
	private DiscountCalculator discountCalculator;
	
	private int accumulativeCardSum;
	
	public TotalOrderPriceCalculator() {
		this.accumulativeCardSum = 0;
	}
	
	public TotalOrderPriceCalculator(int accumulativeCardSum) {
		this.accumulativeCardSum = accumulativeCardSum;
	}


	/**
	 * Calculate total order price including all discounts
	 * 
	 * @param pizzas
	 * @return
	 */
	public int calculateTotalOrderPrice(Map<Pizza, Integer> pizzas) {
		
		int totalOrderPrice = calculateTotalOrderPriceWithoutDiscount(pizzas);
		
		int totalOrderPriceWithDiscount = totalOrderPrice - calculateTotalDiscount(pizzas); 
		
		if (totalOrderPriceWithDiscount < 0) {
			throw new RuntimeException("Total order price < 0");
		}
		
		return totalOrderPriceWithDiscount;
	}
	

	/**
	 * If order contains more than 4 pizzas, then give 50% discount to 
	 * pizza with max price. 
	 * 
	 * @param pizzas
	 * @return
	 */
	public int calculateMaxPizzaPriceDiscount(Map<Pizza, Integer> pizzas) {
		int maxPizzaPrice = 0;
		if (calcTotalPizzasCount(pizzas) > PIZZA_COUNT_TO_GET_DISCOUNT) {
			for(Entry<Pizza, Integer> entry : pizzas.entrySet()) {
				Pizza pizza = entry.getKey();
				maxPizzaPrice = Math.max(maxPizzaPrice, pizza.getPrice());
			}
		}
		
		return (int)(maxPizzaPrice*DISCOUNT_PERCENT_FOR_HIGHEST_PRICE_PIZZA);
	}
	
	/**
	 * Calculate total discount for order: 
	 * includes discount for pizza with max price(if count of pizzas > 4) and
	 * discount for sum on accumulative card
	 * 
	 * @param pizzas
	 * @return
	 */
	public int calculateTotalDiscount(Map<Pizza, Integer> pizzas) {
		int totalOrderPrice = calculateTotalOrderPriceWithoutDiscount(pizzas);
		int maxPizzaPriceDiscount = calculateMaxPizzaPriceDiscount(pizzas);
		int orderPriceWithMaxPizzaPriceDiscount = totalOrderPrice - maxPizzaPriceDiscount;
		
		return maxPizzaPriceDiscount 
				+ discountCalculator.calculateDiscount(orderPriceWithMaxPizzaPriceDiscount,
														accumulativeCardSum);
	}
	
	/**
	 * Calculate total order price without discount
	 * 
	 * @param pizzas
	 * @return
	 */
	public int calculateTotalOrderPriceWithoutDiscount(Map<Pizza, Integer> pizzas) {
		
		validatePizzas(pizzas);
		
		int orderPrice = 0;
		for(Entry<Pizza, Integer> entry : pizzas.entrySet()) {
			Pizza pizza = entry.getKey();
			Integer pizzaCount = entry.getValue();
			orderPrice += pizza.getPrice() * pizzaCount;
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
