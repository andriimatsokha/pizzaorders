package ua.pp.kaeltas.pizzaorders;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.infrastructure.ApplicationContext;
import ua.pp.kaeltas.pizzaorders.infrastructure.Config;
import ua.pp.kaeltas.pizzaorders.infrastructure.JavaConfig;
import ua.pp.kaeltas.pizzaorders.infrastructure.JavaConfigApplicationContext;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;
import ua.pp.kaeltas.pizzaorders.service.OrderService;

public class PizzaApp {
	
	public static void main(String[] args) throws Exception {
        Customer customer = new Customer(1, "Paddy");        
        Order order;

        //pattern: Service Locator
        //ObjectFactory objectFactory = ObjectFactory.getInstance();
		//OrderService orderService = (OrderService) objectFactory.createObject("orderService");

        Config config = new JavaConfig(); 
        
        ApplicationContext context = new JavaConfigApplicationContext(config);
        OrderService orderService = (OrderService) context.getBean("orderService");

        
        //System.out.println(orderService);
        
		//order = orderService.placeNewOrder(customer, 1, 2);
        //System.out.println(order);
    }
	
}
