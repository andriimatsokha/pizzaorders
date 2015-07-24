package ua.pp.kaeltas.pizzaorders;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.service.OrderService;

public class SpringPizzaApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext
			= new ClassPathXmlApplicationContext("appContext.xml");
		
		//PizzaRepository pizzaRepository = 
		//		(PizzaRepository) appContext.getBean("pizzaRepository");
		
		//System.out.println(pizzaRepository);
		
		
		//String[] beanDefinitionNames = appContext.getBeanDefinitionNames();
		//for (String s : beanDefinitionNames) {
		//	System.out.println(s);
		//}
		
		
		Customer customer = new Customer(1, "Paddy");        
        Order order;
        OrderService orderService = appContext.getBean("orderService", OrderService.class);
        
        //System.out.println(orderService);
        
		order = orderService.placeNewOrder(customer, 1, 2);
        System.out.println(order);
		
		
		appContext.close();
	}

}
