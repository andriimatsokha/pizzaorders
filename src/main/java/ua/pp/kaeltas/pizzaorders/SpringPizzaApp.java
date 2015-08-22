package ua.pp.kaeltas.pizzaorders;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.pp.kaeltas.pizzaorders.domain.Address;
import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.repository.AddressRepository;
import ua.pp.kaeltas.pizzaorders.repository.CustomerRepository;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;
import ua.pp.kaeltas.pizzaorders.service.OrderService;

public class SpringPizzaApp {

	public static void main(String[] args) {
//		ConfigurableApplicationContext repositoryContext
//			= new ClassPathXmlApplicationContext("repositoryContext.xml");
		
		ConfigurableApplicationContext appContext
			= new ClassPathXmlApplicationContext(
					new String[] {"appContext.xml"});
		
		PizzaRepository pizzaRepository = 
				appContext.getBean(PizzaRepository.class);
		
		pizzaRepository.createDefaultPizzas();
		
		System.out.println("Pizzas: " + pizzaRepository.getAllPizzas());
		
		
		//System.out.println(pizzaRepository);
		
		
		//String[] beanDefinitionNames = appContext.getBeanDefinitionNames();
		//for (String s : beanDefinitionNames) {
		//	System.out.println(s);
		//}
		
		CustomerRepository customerRepository = appContext.getBean(CustomerRepository.class);
		
		Customer customer = new Customer("Paddy");
		
		customerRepository.save(customer);
		
        Order order;
        OrderService orderService = appContext.getBean(OrderService.class);
//        
//        //System.out.println(orderService);
//        
        
        Address address = new Address();
        address.setStreet("new Street");
        address.setHouseNumber("12");
        
        AddressRepository addressRepository = appContext.getBean(AddressRepository.class);
        addressRepository.save(address);
        
		Order order1 = orderService.placeNewOrder(customer, address, 10, 12);
		Order order2 = orderService.placeNewOrder(customer, address, 11, 12, 12);
		Order order3 = orderService.placeNewOrder(customer, address, 10, 11, 11, 10, 10, 12);
        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        
        
        System.out.println("ORDERS FROM DB:");
        for (Order o : orderService.getAllOrders()) {
        	System.out.println(o);
        }
        
//        System.out.println(appContext.getBean("order", Order.class).toString());
//        System.out.println(appContext.getBean("order", Order.class).toString());
//        
//        System.out.println(appContext.getBean("order", Order.class).toString());
		
		
		appContext.close();
		//repositoryContext.close();
	}

}
