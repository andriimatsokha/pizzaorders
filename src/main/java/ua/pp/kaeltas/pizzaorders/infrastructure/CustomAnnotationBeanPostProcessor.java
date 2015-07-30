package ua.pp.kaeltas.pizzaorders.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomAnnotationBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		
		Object proxyObject = new ProxyForBenchmarkAnnotation().checkAndCreateProxyObjectBenchmark(bean);
		
		return proxyObject;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	
	
}
