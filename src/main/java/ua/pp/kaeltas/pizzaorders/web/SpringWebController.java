package ua.pp.kaeltas.pizzaorders.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.repository.AddressRepository;
import ua.pp.kaeltas.pizzaorders.repository.CustomerRepository;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;
import ua.pp.kaeltas.pizzaorders.service.OrderService;

@Controller
public class SpringWebController {

	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@RequestMapping("hello")
	@ResponseBody
	public String printHello() {
		return "Hello from Spring!";
	}
	
	@RequestMapping("hello2")
	public String printHello2() {
		
		return "helloSpring2";
	}
	
	@RequestMapping("hello3")
	public String printHello3(Model model) {
		model.addAttribute("msg", "Hello Spring View Resolver with params");
		model.addAttribute("date", new Date());
		model.addAttribute("pizzas", pizzaRepository.getAllPizzas());
		
		
		Customer customer = customerRepository.find(20);
		Address address = addressRepository.find(30);
		orderService.placeNewOrder(customer, address, 10, 10, 10, 11);
		
		
		return "helloSpring3";
	}
	
	
}
