package ua.pp.kaeltas.pizzaorders.web;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.domain.PizzaType;
import ua.pp.kaeltas.pizzaorders.service.PizzaService;

@Controller
public class PizzaController extends AbstractPizzaController {

	@Autowired
	PizzaService pizzaService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/pizza/edit", method = RequestMethod.GET)
	public String editPizza(@RequestParam Integer pizzaId, Model model) {
		
		Pizza pizza = pizzaService.getById(pizzaId);
		model.addAttribute("pizza", pizza);
		
		List<PizzaType> pizzaTypes = Arrays.asList(PizzaType.values());
		model.addAttribute("pizzaTypes", pizzaTypes);
		
		return "editPizza";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/pizza/edit", method = RequestMethod.POST)
	public String editPizza(@ModelAttribute Pizza pizza, Model model) {
		
		pizzaService.save(pizza);
		
		return editPizza(pizza.getId(), model);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/pizza/add", method = RequestMethod.GET)
	public String addPizza(Model model) {
		
		List<PizzaType> pizzaTypes = Arrays.asList(PizzaType.values());
		model.addAttribute("pizzaTypes", pizzaTypes);
		
		return "addPizza";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/pizza/add", method = RequestMethod.POST)
	public String addPizza(@ModelAttribute Pizza pizza, Model model) {
		
		pizzaService.save(pizza);
		
		return "redirect:/admin/pizza/add";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/pizza/delete", method = RequestMethod.POST)
	public String deletePizza(@RequestParam Integer pizzaId) {
		
		pizzaService.delete(pizzaId);
		
		return "redirect:/admin/pizza";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/pizza", method = RequestMethod.GET)
	public String editPizza(Model model) {
		
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		
		return "pizzasForEditing";
	}
	
}
