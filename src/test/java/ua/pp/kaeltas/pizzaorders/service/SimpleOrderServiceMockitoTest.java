package ua.pp.kaeltas.pizzaorders.service;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;
import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.repository.OrderRepository;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceMockitoTest {
	@Mock
	private AccumulativeCardService accumulativeCardService;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private PizzaRepository pizzaRepository;
	
	@InjectMocks
	private SimpleOrderService simpleOrderService = spy(SimpleOrderService.class);
	
	@Test
	public void testPlaceNewOrder() throws Exception {
		
		Order order = mock(Order.class);
		doReturn(order).when(simpleOrderService).getNewOrder();
		
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Integer [] pizzasID = new Integer[] {10, 11};
		
		simpleOrderService.placeNewOrder(customer, address, pizzasID);
		
		verify(accumulativeCardService, times(1)).getAccumulativeCard(any(Customer.class), any(Address.class));
		verify(simpleOrderService).getNewOrder();
		verify(accumulativeCardService).incrementTotalSum(any(AccumulativeCard.class), anyMapOf(Pizza.class, Integer.class));
		verify(orderRepository).saveOrder(any(Order.class));
		
	}

}
