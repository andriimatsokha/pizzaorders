package ua.pp.kaeltas.pizzaorders.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;
import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.repository.AccumulativeCardRepository;

@Service
public class AccumulativeCardServiceImpl implements AccumulativeCardService {
	
	private final Logger logger = LogManager.getLogger(AccumulativeCardServiceImpl.class);
	
	@Autowired
	private AccumulativeCardRepository accumulativeCardRepository;
	
	@Autowired
	private TotalOrderPriceService totalOrderPriceService;
	
	@Autowired
	private CustomerService customerService;
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.AccumulativeCardServiceInterface#incrementTotalSum(ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard, java.util.Map)
	 */
	@Override
	public void incrementTotalSum(AccumulativeCard accumulativeCard, Map<Pizza, Integer> pizzas) {

		int totalPrice = totalOrderPriceService.calculateTotalOrderPrice(pizzas, accumulativeCard.getSumOfAllOrders());
		
		accumulativeCard.incrementSumOfAllOrders(totalPrice);
		
		accumulativeCardRepository.update(accumulativeCard);
	}

	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.AccumulativeCardServiceInterface#getAccumulativeCard(ua.pp.kaeltas.pizzaorders.domain.Customer, ua.pp.kaeltas.pizzaorders.domain.Address)
	 */
	@Override
	public AccumulativeCard getAccumulativeCard(Customer customer,
			Address address) {
		AccumulativeCard accumulativeCard = null;
		synchronized(customer) {
	        accumulativeCard = customer.getAccumulativeCard();
	        if (accumulativeCard == null) {
	        	accumulativeCard = createNewAccumulativeCard(customer, address);
	        } else if (accumulativeCard.getAddress() != null && !accumulativeCard.getAddress().equals(address)){
	        	accumulativeCard.setAddress(address);
	        	logger.info("set new address for accumulative card:"
	        			+ " accumulativeCardId = " + accumulativeCard.getId()
	        			+ ", address = " + address);
	        }
        }
		
		return accumulativeCard;
	}
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.AccumulativeCardServiceInterface#getAccumulativeCardSum(ua.pp.kaeltas.pizzaorders.domain.Customer)
	 */
	@Override
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
	
	@Transactional
	private AccumulativeCard createNewAccumulativeCard(Customer customer, Address address) {

		AccumulativeCard accumulativeCard;
		
    	accumulativeCard = new AccumulativeCard();
    	//accumulativeCard.setCustomer(customer);
    	accumulativeCard.setAddress(address);
    	
    	accumulativeCardRepository.save(accumulativeCard);
    	
    	customer.setAccumulativeCard(accumulativeCard);
    	customerService.update(customer);

    	logger.info("create new accumulative card for customerId = " + customer.getId());
		
    	return accumulativeCard;
	}
	
}
