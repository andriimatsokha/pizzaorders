package ua.pp.kaeltas.pizzaorders.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.service.OrderService;

@Controller
@RequestMapping("admin")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * View all orders
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("order/history")
	public String showOrders(Model model) {

		List<Order> allOrders = orderService.getAllOrders();
		
		model.addAttribute("orders", allOrders);
		
		return "ordersHistory";
	}
	
}
