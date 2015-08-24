package ua.pp.kaeltas.pizzaorders.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;
import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.repository.AccumulativeCardRepository;

@Service
public class AccumulativeCardService {
	
	@Autowired
	private AccumulativeCardRepository accumulativeCardRepository;
	
	@Autowired
	private TotalOrderPriceService totalOrderPriceService;
	
	/**
	 * Increment total sum on accumulative card
	 * 
	 * @param accumulativeCard
	 * @param pizzas
	 */
	public void incrementTotalSum(AccumulativeCard accumulativeCard, Map<Pizza, Integer> pizzas) {

		int totalPrice = totalOrderPriceService.calculateTotalOrderPrice(pizzas, accumulativeCard.getSumOfAllOrders());
		
		accumulativeCard.incrementSumOfAllOrders(totalPrice);
		
		accumulativeCardRepository.update(accumulativeCard);
	}

	/**
	 * Get accumulative card for given customer.
	 * If accumulative card does not exist - create it.
	 * 
	 * @param customer
	 * @param address
	 * @return
	 */
	public AccumulativeCard getAccumulativeCard(Customer customer,
			Address address) {
		AccumulativeCard accumulativeCard = null;
		synchronized(customer) {
	        accumulativeCard = customer.getAccumulativeCard();
	        if (accumulativeCard == null) {
	        	accumulativeCard = createNewAccumulativeCard(customer, address);
	        }
        }
		
		return accumulativeCard;
	}
	
	/**
	 * Return total sum of all orders for given customer
	 * 
	 * @param customer
	 * @return
	 */
	public int getAccumulativeCardSum(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Customer must be not null");
		}
		
		AccumulativeCard accumulativeCard = null;
		synchronized(customer) {
	        accumulativeCard = customer.getAccumulativeCard();
	        if (accumulativeCard == null) {
	        	return 0;
	        }
        }
		
		return accumulativeCard.getSumOfAllOrders();
	}
	
	
	private AccumulativeCard createNewAccumulativeCard(Customer customer, Address address) {

		AccumulativeCard accumulativeCard;
		
    	accumulativeCard = new AccumulativeCard();
    	accumulativeCard.setCustomer(customer);
    	accumulativeCard.setAddress(address);
    	accumulativeCardRepository.save(accumulativeCard);
		
    	return accumulativeCard;
	}
	
}
