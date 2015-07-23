package ua.pp.kaeltas.pizzaorders.infrastructure;


public class ObjectFactory {

	private static ObjectFactory instance;
	private Config config = new JavaConfig();
	
	private ObjectFactory() {
		super();
	}

	public static ObjectFactory getInstance() {
		if (instance == null) {
			instance = new ObjectFactory();
		}
		return instance;
	}

	public Object createObject(String beanName) throws Exception {
		
		Class<?> clazz = config.getImplementation(beanName);
		return clazz.newInstance();
	}

}
