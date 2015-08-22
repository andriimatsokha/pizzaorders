package ua.pp.kaeltas.pizzaorders.domain;

public interface DiscountCalculator {
	
	public int calculateDiscount(int orderPrice, int accumulativeCardSum);
	
}
