package ua.pp.kaeltas.pizzaorders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;

@Service
public class PizzaService {

	@Autowired
	PizzaRepository pizzaRepository;
	
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAllPizzas();
	}
	
	public Pizza getById(Integer id) {
		return pizzaRepository.getPizzaByID(id);
	}
	
}
