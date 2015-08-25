package ua.pp.kaeltas.pizzaorders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	PizzaRepository pizzaRepository;
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.PizzaServiceI#getAllPizzas()
	 */
	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAllPizzas();
	}
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.PizzaServiceI#getById(java.lang.Integer)
	 */
	@Override
	public Pizza getById(Integer id) {
		return pizzaRepository.getPizzaByID(id);
	}

	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.PizzaServiceI#save(ua.pp.kaeltas.pizzaorders.domain.Pizza)
	 */
	@Override
	public void save(Pizza pizza) {
		if (pizza.getId() == null) {
			pizzaRepository.savePizza(pizza);
		} else {
			pizzaRepository.update(pizza);
		}
	}

	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.PizzaServiceI#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer pizzaId) {
		pizzaRepository.delete(pizzaId);
	}

}
