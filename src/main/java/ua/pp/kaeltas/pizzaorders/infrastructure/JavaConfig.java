package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ua.pp.kaeltas.pizzaorders.repository.TestOrderRepository;
import ua.pp.kaeltas.pizzaorders.repository.TestPizzaRepository;
import ua.pp.kaeltas.pizzaorders.service.SimpleOrderService;

public class JavaConfig implements Config {

	private Map<String, Class<?>> map = new HashMap<>();
	
	{
		map.put("pizzaRepository", TestPizzaRepository.class);
		map.put("orderRepository", TestOrderRepository.class);
		map.put("orderService", SimpleOrderService.class);
	}
	
	@Override
	public Class<?> getImplementation(String beanName) {
		return map.get(beanName);
	}

}
