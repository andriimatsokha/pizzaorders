package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.lang.reflect.Constructor;

public class JavaConfigApplicationContext implements ApplicationContext {

	private Config config;
	
	public JavaConfigApplicationContext(Config config) {
		this.config = config;
	}

	@Override
	public Object getBean(String beanName) throws Exception {
		
		Class<?> clazz = config.getImplementation(beanName);
		
		Constructor<?> constructor = clazz.getConstructors()[0];
		
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		
		//Parameter[] parameters = constructor.getParameters();
		if (parameterTypes.length == 0) {
			return clazz.newInstance();
		}
		
		Object [] constructorParams= new Object[parameterTypes.length];
		
		int i = 0;
		for(Class<?> pt : parameterTypes) {
			String className = firstCharToLowerCase(pt.getSimpleName());
			constructorParams[i++] = getBean(className);
		}
		
		return constructor.newInstance(constructorParams);
	}

	private String firstCharToLowerCase(String name) {
		
		if (name.isEmpty()) {
			return name;
		}

		String className;
		if (name.length() > 1) {
			className = Character.toLowerCase(name.charAt(0)) 
					+ name.substring(1);
		} else {
			className = Character.toLowerCase(name.charAt(0)) + "";
		}
		return className;
	}

}
