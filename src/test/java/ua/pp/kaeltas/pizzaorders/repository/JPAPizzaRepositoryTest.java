package ua.pp.kaeltas.pizzaorders.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.domain.PizzaType;
import ua.pp.kaeltas.pizzaorders.repository.template.ITRepositoryTestTemplate;

public class JPAPizzaRepositoryTest extends ITRepositoryTestTemplate {

	@Autowired
	PizzaRepository pizzaRepository;
	
	@Test
	public void testSavePizza() throws Exception {
		
		Pizza pizza = new Pizza();
		pizza.setName("Test pizza");
		pizza.setPrice(789);
		pizza.setType(PizzaType.SEA);
		
		pizzaRepository.savePizza(pizza);
		
		System.out.println("pizzaId = " + pizza.getId());
		
		assertNotNull(pizza.getId());
		
		
	}

}
