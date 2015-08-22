package ua.pp.kaeltas.pizzaorders.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/appContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",
		defaultRollback = true)
public class TotalOrderPriceCalculatorTest {

	@Autowired
	TotalOrderPriceCalculator totalOrderCostCalculator;
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithZeroPizzaThrowException() throws Exception {
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		new TotalOrderPriceCalculator().calculateTotalOrderPrice(pizzas);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithMoreThan10PizzasOfOneTypeThrowException() throws Exception {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "4Cheese", 123, PizzaType.VEGETARIAN), 11);
		
		new TotalOrderPriceCalculator().calculateTotalOrderPrice(pizzas);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithMoreThan10PizzasOfDifferentTypesThrowException() throws Exception {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "4Cheese", 123, PizzaType.VEGETARIAN), 4);
		pizzas.put(new Pizza(2, "5Cheese", 14, PizzaType.VEGETARIAN), 4);
		pizzas.put(new Pizza(3, "6Cheese", 15, PizzaType.VEGETARIAN), 3);
		
		new TotalOrderPriceCalculator().calculateTotalOrderPrice(pizzas);
	}

	@Test
	public void testCalculateTotalOrderPriceWithOnePizza() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice = 56;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice, PizzaType.VEGETARIAN), 1);
		int expectedOrderPrice = 56;
		
//		TotalOrderPriceCalculator totalOrderCostCalculator = new TotalOrderPriceCalculator();
		int actualOrderPrice = totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);

		assertEquals(expectedOrderPrice, actualOrderPrice);
	}
	
	@Test
	public void testCalculateTotalOrderPriceWithTenPizza() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice = 10;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice, PizzaType.VEGETARIAN), 5);
		pizzas.put(new Pizza(2, "2Cheese", pizzaPrice, PizzaType.VEGETARIAN), 5);
		int expectedOrderPrice = 95; //100 - 5(discount for max price pizza)
		
		//TotalOrderPriceCalculator totalOrderCostCalculator = new TotalOrderPriceCalculator();
		int actualOrderPrice = totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);
		
		assertEquals(expectedOrderPrice, actualOrderPrice);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithNegativePizzaPriceThrowException() throws Exception {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice = -20;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice, PizzaType.VEGETARIAN), 1);
		
		new TotalOrderPriceCalculator().calculateTotalOrderPrice(pizzas);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithNegativePizzaCountThrowException() throws Exception {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice = 20;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice, PizzaType.VEGETARIAN), 5);
		pizzas.put(new Pizza(2, "2Cheese", pizzaPrice, PizzaType.VEGETARIAN), -2);
		
		new TotalOrderPriceCalculator().calculateTotalOrderPrice(pizzas);
	}
	
	@Test
	public void testCalculatePizzaDiscountWith5Pizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice1 = 11;
		int pizzaPrice2 = 22;
		int pizzaPrice3 = 33;
		int pizzaPrice4 = 50;
		int pizzaPrice5 = 10;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice1, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(2, "3Cheese", pizzaPrice2, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(3, "2Cheese", pizzaPrice3, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(4, "1Cheese", pizzaPrice4, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(5, "0Cheese", pizzaPrice5, PizzaType.VEGETARIAN), 1);
		int expectedPizzaDiscount = 25;
		
		TotalOrderPriceCalculator totalOrderCostCalculator = new TotalOrderPriceCalculator();
		int actualPizzaDiscount = totalOrderCostCalculator.calculatePizzaDiscount(pizzas);
		
		assertEquals(expectedPizzaDiscount, actualPizzaDiscount);
	}
	
	@Test
	public void testCalculatePizzaDiscountWithTwoHighestPricePizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice1 = 11;
		int pizzaPrice2 = 22;
		int pizzaPrice3 = 33;
		int pizzaPrice4 = 50;
		int pizzaPrice5 = 50;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice1, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(2, "3Cheese", pizzaPrice2, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(3, "2Cheese", pizzaPrice3, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(4, "1Cheese", pizzaPrice4, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(5, "0Cheese", pizzaPrice5, PizzaType.VEGETARIAN), 1);
		int expectedPizzaDiscount = 25;
		
		TotalOrderPriceCalculator totalOrderCostCalculator = new TotalOrderPriceCalculator();
		int actualPizzaDiscount = totalOrderCostCalculator.calculatePizzaDiscount(pizzas);
		
		assertEquals(expectedPizzaDiscount, actualPizzaDiscount);
	}
	
	@Test
	public void testCalculatePizzaDiscountWith4Pizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int pizzaPrice1 = 11;
		int pizzaPrice2 = 22;
		int pizzaPrice3 = 33;
		int pizzaPrice4 = 43;
		pizzas.put(new Pizza(1, "4Cheese", pizzaPrice1, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(2, "3Cheese", pizzaPrice2, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(3, "2Cheese", pizzaPrice3, PizzaType.VEGETARIAN), 1);
		pizzas.put(new Pizza(4, "1Cheese", pizzaPrice4, PizzaType.VEGETARIAN), 1);
		int expectedPizzaDiscount = 0;
		
		TotalOrderPriceCalculator totalOrderCostCalculator = new TotalOrderPriceCalculator();
		int actualPizzaDiscount = totalOrderCostCalculator.calculatePizzaDiscount(pizzas);
		
		assertEquals(expectedPizzaDiscount, actualPizzaDiscount);
	}

	
}
