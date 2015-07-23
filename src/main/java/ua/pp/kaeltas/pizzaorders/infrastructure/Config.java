package ua.pp.kaeltas.pizzaorders.infrastructure;

public interface Config {

	Class<?> getImplementation(String beanName);
	
}
