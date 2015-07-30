package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		
		//>>>Added for compatibility with proxy, added by Spring's <lookup-method>
		Set<Class<?>> interfaces = new HashSet<>();
		interfaces.addAll(Arrays.asList(type.getInterfaces()));
		for (Method m : type.getMethods()) {
			interfaces.addAll(Arrays.asList(m.getDeclaringClass().getInterfaces()));
		}
		//<<<Added for compatibility with proxy, added by Spring's <lookup-method>
		
		return Proxy.newProxyInstance(
				type.getClassLoader(), 
				interfaces.toArray(new Class[0]), 
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
