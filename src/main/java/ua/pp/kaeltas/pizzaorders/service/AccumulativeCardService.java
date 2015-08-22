package ua.pp.kaeltas.pizzaorders.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;
import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.domain.TotalOrderPriceCalculator;
import ua.pp.kaeltas.pizzaorders.repository.AccumulativeCardRepository;

@Service
public class AccumulativeCardService {
	
	@Autowired
	private AccumulativeCardRepository accumulativeCardRepository;
	
	public AccumulativeCard createNewAccumulativeCard(Customer customer, Address address) {

		AccumulativeCard accumulativeCard;
		
    	accumulativeCard = new AccumulativeCard();
    	accumulativeCard.setCustomer(customer);
    	accumulativeCard.setAddress(address);
    	accumulativeCardRepository.save(accumulativeCard);
		
    	return accumulativeCard;
	}
	
	public void incrementTotalSum(AccumulativeCard accumulativeCard, Map<Pizza, Integer> pizzas) {
		TotalOrderPriceCalculator totalOrderPriceCalculator = new TotalOrderPriceCalculator();
		int totalPrice = totalOrderPriceCalculator.calculateTotalOrderPrice(pizzas);
		
		accumulativeCard.incrementSumOfAllOrders(totalPrice);
		
		accumulativeCardRepository.update(accumulativeCard);
	}
	
}
