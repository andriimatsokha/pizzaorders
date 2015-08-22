package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;
import ua.pp.kaeltas.pizzaorders.domain.Customer;

public interface AccumulativeCardRepository {

	AccumulativeCard find(Integer id);
	Integer save(AccumulativeCard accumulativeCard);
	void update(AccumulativeCard accumulativeCard);
	
	
}
