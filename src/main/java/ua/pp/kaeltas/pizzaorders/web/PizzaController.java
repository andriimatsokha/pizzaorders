package ua.pp.kaeltas.pizzaorders.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.service.PizzaService;

@Controller
public class PizzaController {

	@Autowired
	PizzaService pizzaService;
	
	@RequestMapping(value = "admin/pizza/edit", method = RequestMethod.GET)
	public String editPizza(@RequestParam Integer pizzaId, Model model) {
		
		Pizza pizza = pizzaService.getById(pizzaId);
		model.addAttribute("pizza", pizza);
		
		return "editPizza";
	}
	
	@RequestMapping(value = "admin/pizza", method = RequestMethod.GET)
	public String editPizza(Model model) {
		
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		
		return "pizzasForEditing";
	}
	
}
