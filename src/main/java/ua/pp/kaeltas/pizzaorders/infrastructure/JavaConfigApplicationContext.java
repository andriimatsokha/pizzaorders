package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import ua.pp.kaeltas.pizzaorders.service.OrderService;

public class JavaConfigApplicationContext implements ApplicationContext {

	private Config config;
	private Map<String, Object> beans = new HashMap<>();
	
	public JavaConfigApplicationContext(Config config) {
		this.config = config;
	}

	@Override
	public Object getBean(String beanName) throws Exception {
		
		Object bean = beans.get(beanName);
		if (bean != null) {
			return bean;
		}
		
		BeanBuilder builder = new BeanBuilder(beanName);
		builder.createObject();
		builder.callInitMethod();
		builder.createProxy();
		
		return builder.getObject();
		
//		Class<?> clazz = config.getImplementation(beanName);
//		
//		Constructor<?> constructor = clazz.getConstructors()[0];
//		
//		Class<?>[] parameterTypes = constructor.getParameterTypes();
//		
//		//Parameter[] parameters = constructor.getParameters();
//		if (parameterTypes.length == 0) {
//			bean = clazz.newInstance();
//			beans.put(beanName, bean);
//			return bean;
//		}
//		
//		Object [] constructorParams= new Object[parameterTypes.length];
//		
//		int i = 0;
//		for(Class<?> pt : parameterTypes) {
//			String className = firstCharToLowerCase(pt.getSimpleName());
//			constructorParams[i++] = getBean(className);
//		}
//		
//		bean = constructor.newInstance(constructorParams);
//		beans.put(beanName, bean);
//		return bean;
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
	
	class BeanBuilder {
		
		private Object obj;
		private String beanName; 
		
		private BeanBuilder(String beanName) {
			this.beanName = beanName;
		}

		public void createObject() throws Exception {
			
			Class<?> clazz = config.getImplementation(beanName);
			
			Constructor<?> constructor = clazz.getConstructors()[0];
			
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			
			//Parameter[] parameters = constructor.getParameters();
			if (parameterTypes.length == 0) {
				obj = clazz.newInstance();
				beans.put(beanName, obj);
				return;
			}
			
			Object [] constructorParams= new Object[parameterTypes.length];
			
			int i = 0;
			for(Class<?> pt : parameterTypes) {
				String className = firstCharToLowerCase(pt.getSimpleName());
				constructorParams[i++] = getBean(className);
			}
			
			obj = constructor.newInstance(constructorParams);
			beans.put(beanName, obj);
			
			//return this;
		}
		
		public void callInitMethod() throws Exception {

			Class<?> clazz = obj.getClass();
			
			try {
				System.out.println("-----INIT " + clazz);
				Method method = clazz.getMethod("init");
				method.invoke(obj);
			} catch (NoSuchMethodException exception) {
				return;
			}
			
		}
		
		public Object getObject() {
			return obj;
		}
		
		public void createProxy() {
			
			ProxyForBenchmarkAnnotation proxyForBenchmarkAnnotation 
				= new ProxyForBenchmarkAnnotation();
			obj = proxyForBenchmarkAnnotation.checkAndCreateProxyObjectBenchmark(obj);
		}

		
	}

}
