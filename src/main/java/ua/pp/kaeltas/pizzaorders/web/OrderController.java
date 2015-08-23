package ua.pp.kaeltas.pizzaorders.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.repository.CustomerRepository;
import ua.pp.kaeltas.pizzaorders.service.AccumulativeCardService;
import ua.pp.kaeltas.pizzaorders.service.PizzaService;
import ua.pp.kaeltas.pizzaorders.service.TotalOrderPriceService;


@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private TotalOrderPriceService totalOrderPriceService;
	
	@Autowired
	private AccumulativeCardService accumulativeCardService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping("pizza/select")
	public String showPizzas(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", auth.getName());
        //model.addAttribute("roles", auth.getAuthorities().toString());
		
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		
		return "selectPizza";
	}
	
	/**
	 * Add one pizza with id = pizzaId to cart. Cart is stored in session.
	 * If cart already contains such pizza - increment it's count by 1. 
	 * 
	 * @param pizzaId
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("pizza/addtocart")
	public String addToCart(@RequestParam Integer pizzaId, 
			HttpServletRequest httpServletRequest, Model model) {
		HttpSession httpSession = httpServletRequest.getSession();
		
		//TODO test customer
		Customer customer1 = customerRepository.find(20); 
		httpSession.setAttribute("customer", customer1);
		
		Map<Pizza, Integer> cart = (Map<Pizza, Integer>) httpSession.getAttribute("cart");
		if (cart == null) {
			cart = new ConcurrentHashMap<Pizza, Integer>();
			httpSession.setAttribute("cart", cart);
		}
	
		incrementPizzaCountInCart(pizzaId, cart);
		
		Customer customer = (Customer)httpSession.getAttribute("customer");
		
		int accumulativeCardSum = accumulativeCardService.getAccumulativeCardSum(customer);
		
		int totalOrderPriceWoDiscount = totalOrderPriceService.calculateTotalOrderPriceWithoutDiscount(cart, accumulativeCardSum);
		httpSession.setAttribute("totalOrderPriceWoDiscount", totalOrderPriceWoDiscount);
		httpSession.setAttribute("orderDiscount", totalOrderPriceService.calculateOrderDiscount(cart, accumulativeCardSum));
				
		return "redirect:/order/showcart";
	}

	private void incrementPizzaCountInCart(Integer pizzaId,
			Map<Pizza, Integer> cart) {
		Pizza pizza = pizzaService.getById(pizzaId);
		
		if (cart.containsKey(pizza)) {
			cart.put(pizza, cart.get(pizza)+1);
		} else {
			cart.put(pizza, 1);
		}
	}
	
	@RequestMapping("showcart")
	public String showCart(Model model) {
		
		return "showCart";
	}
	
	@RequestMapping("checkout")
	public String checkout(HttpServletRequest httpServletRequest, Model model) {
		
		HttpSession httpSession = httpServletRequest.getSession();
		Map<Pizza, Integer> cart = (Map<Pizza, Integer>)httpSession.getAttribute("cart");
		
		
		model.addAttribute("totalOrderPriceWoDiscount", httpSession.getAttribute("totalOrderPriceWoDiscount"));
		model.addAttribute("orderDiscount", httpSession.getAttribute("orderDiscount"));
		model.addAttribute("cart", cart);
		
		httpSession.invalidate();
		
		return "orderConfirmed";
	}
	
	
}
