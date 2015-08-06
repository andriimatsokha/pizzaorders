package ua.pp.kaeltas.pizzaorders.repository;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.domain.PizzaType;
import ua.pp.kaeltas.pizzaorders.infrastructure.Benchmark;

public class TestPizzaRepository implements PizzaRepository {

	private List<Pizza> pizzas;
	
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.repository.PizzaRepository#getPizzaByID(java.lang.Integer)
	 */
	@Override
	@Benchmark
	public Pizza getPizzaByID(Integer id) {
		for (Pizza p : pizzas) {
			if (p.getId() == id) {
				return p;
			}
		}
		
		throw new NoSuchElementException();
	}
	
	
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}



	public void init() {
		
		pizzas = Arrays.asList(
				new Pizza[] {
						new Pizza(1, "Four Cheese", 55, PizzaType.VEGETARIAN),
						new Pizza(2, "Mussels", 66, PizzaType.SEA),
						new Pizza(3, "Four Meats", 77, PizzaType.MEAT)
						});
		
	}



	@Override
	public void savePizza(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Pizza> getAllPizzas() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
