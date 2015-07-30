package ua.pp.kaeltas.pizzaorders.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cglib.proxy.Enhancer;

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
		Class<?> [] interfacesAr;
		
		//solution1
		if (Enhancer.isEnhanced(type)) {
			interfacesAr = type.getSuperclass().getInterfaces();
		} else {
			interfacesAr = type.getInterfaces();
		}
		
		//solution2
//		Set<Class<?>> interfaces = new HashSet<>();
//	    interfaces.addAll(Arrays.asList(type.getInterfaces()));
//	    interfaces.addAll(Arrays.asList(type.getSuperclass().getInterfaces()));
//	    interfacesAr = interfaces.toArray(new Class[0]);
	    
	    
		//<<<Added for compatibility with proxy, added by Spring's <lookup-method>
		
		return Proxy.newProxyInstance(
				type.getClassLoader(), 
				interfacesAr,
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
