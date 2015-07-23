package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class JavaConfigApplicationContext implements ApplicationContext {

	private Config config;
	
	public JavaConfigApplicationContext(Config config) {
		this.config = config;
	}

	@Override
	public Object getBean(String beanName) throws Exception {
		
		Class<?> clazz = config.getImplementation(beanName);
		
		//clazz.newInstance();
		
		Constructor<?> constructor = clazz.getConstructors()[0];
		
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		
		Parameter[] parameters = constructor.getParameters();
		if (parameters.length == 0) {
			return clazz.newInstance();
		}
		
		
		return null;
	}

}
