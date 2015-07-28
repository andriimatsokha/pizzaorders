package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyForBenchmarkAnnotation {

	public Object checkAndCreateProxyObjectBenchmark(Object o) {
		Class<?> clazz = o.getClass();
		
		for (Method m : clazz.getMethods()) {
			if (m.isAnnotationPresent(Benchmark.class)) {
				return createProxyObj(o);
			}
		}
		return o;
	}

	private Object createProxyObj(final Object o) {
		final Class<?> type = o.getClass();
		
		return Proxy.newProxyInstance(
				type.getClassLoader(), 
				type.getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(
							Object proxy, 
							Method method, 
							Object[] args) throws Throwable {
						
						if (type.getMethod(method.getName(), method.getParameterTypes())
								.isAnnotationPresent(Benchmark.class)) {
						
							System.out.println("Benchmark start: " + method.getName());
				
							long nanoTimeStart = System.nanoTime();
							Object retVal = method.invoke(o, args);
							long nanoTimeResult = System.nanoTime() - nanoTimeStart;
							
							System.out.println("Result: " + nanoTimeResult);
							System.out.println("Benchmark finished: " + method.getName());
							
							return retVal;
						} else {
							return method.invoke(o, args);
						}
					}
				});
		
		//return null;
	}
	
}
