package ua.pp.kaeltas.pizzaorders.web;

import java.beans.PropertyEditorSupport;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.exception.NotFoundPizzaException;
import ua.pp.kaeltas.pizzaorders.service.AccumulativeCardService;
import ua.pp.kaeltas.pizzaorders.service.AddressService;
import ua.pp.kaeltas.pizzaorders.service.CustomerService;
import ua.pp.kaeltas.pizzaorders.service.OrderService;
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
	private CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	AddressService addressService;
	
	/**
	 * View all pizzas for customer
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("pizza/select")
	public String showPizzas(HttpServletRequest httpServletRequest, Model model) {
		
		HttpSession session = httpServletRequest.getSession();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //model.addAttribute("name", auth.getName());
        //model.addAttribute("roles", auth.getAuthorities().toString());
        
        Customer customer = customerService.getByName(auth.getName()); 
        session.setAttribute("customer", customer);
        int accCardSum = accumulativeCardService.getAccumulativeCardSum(customer);
        
        
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		model.addAttribute("accCardSum", accCardSum);
		
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
			HttpServletRequest httpServletRequest, 
			Model model) {
		HttpSession httpSession = httpServletRequest.getSession();
		
		@SuppressWarnings("unchecked")
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
	
	/**
	 * View customer's current cart
	 * @param model
	 * @return
	 */
	@RequestMapping("showcart")
	public String showCart(Model model) {
		
		return "showCart";
	}
	
	/**
	 * Checkout customer's current order
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("checkout")
	public String checkout(@ModelAttribute Address address, HttpServletRequest httpServletRequest, Model model) {
		
		HttpSession httpSession = httpServletRequest.getSession();
		@SuppressWarnings("unchecked")
		Map<Pizza, Integer> cart = (Map<Pizza, Integer>)httpSession.getAttribute("cart");
		
		Customer customer = (Customer)httpSession.getAttribute("customer");
		Address ormAddress = addressService.find(address);
		
		orderService.placeNewOrder(customer, ormAddress, cart);
		
//		System.out.println("address = " + address);
//		System.out.println("address = " + address.equals(customerCurrentAddress));
		
		model.addAttribute("totalOrderPriceWoDiscount", httpSession.getAttribute("totalOrderPriceWoDiscount"));
		model.addAttribute("orderDiscount", httpSession.getAttribute("orderDiscount"));
		model.addAttribute("cart", cart);
		
		httpSession.setAttribute("cart", null);
		httpSession.setAttribute("totalOrderPriceWoDiscount", null);
		httpSession.setAttribute("orderDiscount", null);
		
		return "orderConfirmed";
	}
	
}
