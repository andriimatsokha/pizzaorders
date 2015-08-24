package ua.pp.kaeltas.pizzaorders.service;

import java.util.List;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;

public interface PizzaService {

	public abstract List<Pizza> getAllPizzas();

	public abstract Pizza getById(Integer id);

	public abstract void save(Pizza pizza);

	public abstract void delete(Integer pizzaId);

}