package ua.pp.kaeltas.pizzaorders.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PercentOfTotalOrderDiscountCalculatorTest {

	@Test
	public void testCalculateDiscount10Percent() throws Exception {
		DiscountCalculator discountCalculator = new PercentOfTotalOrderDiscountCalculator();
		int expectedDiscount = 10;
		int orderPrice = 100;
		int accumulativeCardSum = 1000;
		int actualDiscount = discountCalculator.calculateDiscount(orderPrice, accumulativeCardSum);
		
		assertEquals(expectedDiscount, actualDiscount);
	}

	@Test
	public void testCalculateDiscount20Percent() throws Exception {
		DiscountCalculator discountCalculator = new PercentOfTotalOrderDiscountCalculator();
		int expectedDiscount = 20;
		int orderPrice = 100;
		int accumulativeCardSum = 2000;
		int actualDiscount = discountCalculator.calculateDiscount(orderPrice, accumulativeCardSum);
		
		assertEquals(expectedDiscount, actualDiscount);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateDiscountNegativeAccSumThrowsException() throws Exception {
		DiscountCalculator discountCalculator = new PercentOfTotalOrderDiscountCalculator();
		int orderPrice = 100;
		int accumulativeCardSum = -1000;
		discountCalculator.calculateDiscount(orderPrice, accumulativeCardSum);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateDiscountNegativeOrderPriceThrowsException() throws Exception {
		DiscountCalculator discountCalculator = new PercentOfTotalOrderDiscountCalculator();
		int orderPrice = -100;
		int accumulativeCardSum = 1000;
		discountCalculator.calculateDiscount(orderPrice, accumulativeCardSum);
	}
}
