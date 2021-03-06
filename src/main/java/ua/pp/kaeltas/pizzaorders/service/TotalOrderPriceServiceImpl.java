package ua.pp.kaeltas.pizzaorders.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.domain.TotalOrderPriceCalculator;

@Service
public class TotalOrderPriceServiceImpl implements TotalOrderPriceService {

	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.TotalOrderPriceServiceI#calculateTotalOrderPrice(java.util.Map, int)
	 */
	@Override
	public int calculateTotalOrderPrice(Map<Pizza, Integer> pizzas, int accumulativeCardSum) {
		
		TotalOrderPriceCalculator totalOrderPriceCalculator = getNewTotalOrderPriceCalculator(accumulativeCardSum);
		int totalPrice = totalOrderPriceCalculator.calculateTotalOrderPrice(pizzas);
		
		return totalPrice;
	}
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.TotalOrderPriceServiceI#calculateTotalOrderPriceWithoutDiscount(java.util.Map, int)
	 */
	@Override
	public int calculateTotalOrderPriceWithoutDiscount(Map<Pizza, Integer> pizzas, int accumulativeCardSum) {
		
		TotalOrderPriceCalculator totalOrderPriceCalculator = getNewTotalOrderPriceCalculator(accumulativeCardSum);
		int totalPrice = totalOrderPriceCalculator.calculateTotalOrderPriceWithoutDiscount(pizzas);
		
		return totalPrice;
	}
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.TotalOrderPriceServiceI#calculateOrderDiscount(java.util.Map, int)
	 */
	@Override
	public int calculateOrderDiscount(Map<Pizza, Integer> pizzas, int accumulativeCardSum) {
		TotalOrderPriceCalculator totalOrderPriceCalculator = getNewTotalOrderPriceCalculator(accumulativeCardSum);
		int orderDiscount = totalOrderPriceCalculator.calculateTotalDiscount(pizzas);
		
		return orderDiscount;
	}
	
	@Lookup
	protected TotalOrderPriceCalculator getNewTotalOrderPriceCalculator(int accumulativeCardSum) {
		throw new UnsupportedOperationException();
	}
	
}
