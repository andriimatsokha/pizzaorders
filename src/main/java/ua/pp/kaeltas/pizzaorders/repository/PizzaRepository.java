package ua.pp.kaeltas.pizzaorders.repository;

import java.util.List;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;

public interface PizzaRepository {

	public abstract Pizza getPizzaByID(Integer id);
	public abstract void savePizza(Pizza pizza);
	public abstract void update(Pizza pizza);
	public List<Pizza> getAllPizzas();
	public abstract void createDefaultPizzas();
	public abstract void delete(Integer pizzaId);

}