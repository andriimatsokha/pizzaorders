package ua.pp.kaeltas.pizzaorders.service;

import java.util.Map;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;
import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;

public interface AccumulativeCardService {

	/**
	 * Increment total sum on accumulative card
	 * 
	 * @param accumulativeCard
	 * @param pizzas
	 */
	public abstract void incrementTotalSum(AccumulativeCard accumulativeCard,
			Map<Pizza, Integer> pizzas);

	/**
	 * Get accumulative card for given customer and update current address.
	 * If accumulative card does not exist - create it.
	 * 
	 * @param customer
	 * @param address
	 * @return
	 */
	public abstract AccumulativeCard getAccumulativeCard(Customer customer,
			Address address);

	/**
	 * Return total sum of all orders for given customer
	 * 
	 * @param customer
	 * @return
	 */
	public abstract int getAccumulativeCardSum(Customer customer);

}