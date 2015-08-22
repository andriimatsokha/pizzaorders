package ua.pp.kaeltas.pizzaorders.domain;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TotalOrderPriceCalculatorMockitoTest {

	@InjectMocks
    private TotalOrderPriceCalculator totalOrderPriceCalculator;
    @Mock
    private DiscountCalculator discountCalculator;
    
    @Test
	public void testCalculateTotalOrderPrice() throws Exception {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "4Cheese", 10, PizzaType.VEGETARIAN), 1);
		
		totalOrderPriceCalculator.calculateTotalOrderPrice(pizzas);
		
		verify(discountCalculator).calculateDiscount(anyInt(), anyInt());
		
	}
	
}
