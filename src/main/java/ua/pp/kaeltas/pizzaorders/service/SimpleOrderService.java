package ua.pp.kaeltas.pizzaorders.service;

import java.util.ArrayList;
import java.util.List;

import ua.pp.kaeltas.pizzaorders.domain.Customer;
import ua.pp.kaeltas.pizzaorders.domain.Order;
import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.infrastructure.Benchmark;
import ua.pp.kaeltas.pizzaorders.repository.OrderRepository;
import ua.pp.kaeltas.pizzaorders.repository.PizzaRepository;

public class SimpleOrderService implements OrderService/*, ApplicationContextAware*/ {
	
	//private ObjectFactory objectFactory = ObjectFactory.getInstance();
	//private ApplicationContext appContext;
	
	private PizzaRepository pizzaRepository;
	private OrderRepository orderRepository;
	
	
	public SimpleOrderService(
			PizzaRepository pizzaRepository,
			OrderRepository orderRepository) {
		this.pizzaRepository = pizzaRepository;
		this.orderRepository = orderRepository;
	}



	//public SimpleOrderService() throws Exception {
//		this.pizzaRepository = (PizzaRepository) objectFactory.createObject("pizzaRepository");
//		this.orderRepository = (OrderRepository) objectFactory.createObject("orderRepository");
	//}
	
	
	
	/* (non-Javadoc)
	 * @see ua.pp.kaeltas.pizzaorders.service.OrderService#placeNewOrder(ua.pp.kaeltas.pizzaorders.domain.Customer, java.lang.Integer)
	 */
	@Override
	@Benchmark
	public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();
       
        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = getNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);
       
        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }



	protected Order getNewOrder() {
		throw new UnsupportedOperationException("This method must be overriden by Spring");
		//return null;
		//Order newOrder = appContext.getBean("order", Order.class);
		//Order newOrder = new Order();
		//return newOrder;
	}



//	@Override
//	public void setApplicationContext(ApplicationContext ac)
//			throws BeansException {
//		this.appContext = ac;
//	}


	
}
