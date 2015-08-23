package ua.pp.kaeltas.pizzaorders.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.pp.kaeltas.pizzaorders.service.PizzaService;

@Controller
public class IndexController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		
		return "index";
	}
	
}
