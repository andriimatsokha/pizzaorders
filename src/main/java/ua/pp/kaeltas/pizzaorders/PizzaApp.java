package ua.pp.kaeltas.pizzaorders;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.infrastructure.ApplicationContext;
import ua.pp.kaeltas.pizzaorders.infrastructure.Config;
import ua.pp.kaeltas.pizzaorders.infrastructure.JavaConfig;
import ua.pp.kaeltas.pizzaorders.infrastructure.JavaConfigApplicationContext;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;

public class PizzaApp {
	
	public static void main(String[] args) throws Exception {
        Customer customer = new Customer(1, "Paddy");        
        Order order;
        
        //ObjectFactory objectFactory = ObjectFactory.getInstance();
		//OrderService orderService = (OrderService) objectFactory.createObject("orderService");

        Config config = new JavaConfig(); 
        
        ApplicationContext context = new JavaConfigApplicationContext(config);
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        
        System.out.println(pizzaRepository);
        
		//order = orderService.placeNewOrder(customer, 1, 2);
        //System.out.println(order);
    }
	
}
