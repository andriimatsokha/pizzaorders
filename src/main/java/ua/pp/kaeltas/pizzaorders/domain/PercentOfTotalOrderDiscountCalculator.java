package ua.pp.kaeltas.pizzaorders.domain;

public class PercentOfTotalOrderDiscountCalculator implements DiscountCalculator {

	private final int SUM_FOR_10_PERCENT_DISCOUNT = 1000;
	private final int SUM_FOR_20_PERCENT_DISCOUNT = 2000;
	
	@Override
	public int calculateDiscount(int orderPrice, int accumulativeCardSum) {
		if (orderPrice < 0 || accumulativeCardSum < 0) {
			throw new IllegalArgumentException("Negative orderPrice or accumulative card sum");
		}
		
		int discount = 0;
		if (accumulativeCardSum >= SUM_FOR_20_PERCENT_DISCOUNT) {
			discount = orderPrice * 20/100; 
		} else if (accumulativeCardSum >= SUM_FOR_10_PERCENT_DISCOUNT) {
			discount = orderPrice * 10/100; 
		}
		
		return discount;
	}
	
}
