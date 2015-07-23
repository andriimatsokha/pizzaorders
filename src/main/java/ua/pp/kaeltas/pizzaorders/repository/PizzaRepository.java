package ua.pp.kaeltas.pizzaorders.repository;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;

public interface PizzaRepository {

	public abstract Pizza getPizzaByID(Integer id);

}