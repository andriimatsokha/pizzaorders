package ua.pp.kaeltas.pizzaorders;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.service.OrderService;

public class SpringPizzaApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext repositoryContext
			= new ClassPathXmlApplicationContext("repositoryContext.xml");
		
		ConfigurableApplicationContext appContext
		= new ClassPathXmlApplicationContext(
				new String[] {"appContext.xml"}, 
				repositoryContext);
		
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
        
		Order order1 = orderService.placeNewOrder(customer, 1, 2);
		Order order2 = orderService.placeNewOrder(customer, 1, 2);
		Order order3 = orderService.placeNewOrder(customer, 1, 2);
        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        
//        System.out.println(appContext.getBean("order", Order.class).toString());
//        System.out.println(appContext.getBean("order", Order.class).toString());
//        
//        System.out.println(appContext.getBean("order", Order.class).toString());
		
		
		appContext.close();
		repositoryContext.close();
	}

}
