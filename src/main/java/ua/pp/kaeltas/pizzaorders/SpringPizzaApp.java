package ua.pp.kaeltas.pizzaorders;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;

public class SpringPizzaApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext repositoryContext
			= new ClassPathXmlApplicationContext("repositoryContext.xml");
		
		ConfigurableApplicationContext appContext
			= new ClassPathXmlApplicationContext(
					new String[] {"appContext.xml"}, 
					repositoryContext);
		
		PizzaRepository pizzaRepository = 
				appContext.getBean(PizzaRepository.class);
		
//		Pizza pizza = new Pizza();
//		pizza.setName("4 Meat");
//		pizza.setPrice(66);
//		pizza.setType(PizzaType.MEAT);
//		pizzaRepository.savePizza(pizza);
//		
//		Pizza pizza2 = new Pizza();
//		pizza2.setName("4 Cheese");
//		pizza2.setPrice(54);
//		pizza2.setType(PizzaType.VEGETARIAN);
//		pizzaRepository.savePizza(pizza2);
		
		System.out.println("Pizzas: " + pizzaRepository.getAllPizzas());
		
		//System.out.println(pizzaRepository);
		
		
		//String[] beanDefinitionNames = appContext.getBeanDefinitionNames();
		//for (String s : beanDefinitionNames) {
		//	System.out.println(s);
		//}
		
		
//		Customer customer = new Customer(1, "Paddy");        
//        Order order;
//        OrderService orderService = appContext.getBean("orderService", OrderService.class);
//        
//        //System.out.println(orderService);
//        
//		Order order1 = orderService.placeNewOrder(customer, 1, 2);
//		Order order2 = orderService.placeNewOrder(customer, 1, 2);
//		Order order3 = orderService.placeNewOrder(customer, 1, 2);
//        System.out.println(order1);
//        System.out.println(order2);
//        System.out.println(order3);
        
//        System.out.println(appContext.getBean("order", Order.class).toString());
//        System.out.println(appContext.getBean("order", Order.class).toString());
//        
//        System.out.println(appContext.getBean("order", Order.class).toString());
		
		
		appContext.close();
		repositoryContext.close();
	}

}
