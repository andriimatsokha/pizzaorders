package ua.pp.kaeltas.pizzaorders.domain;

import java.util.List;

public class Order {

	static int stId = 10;
	
	private int id;
	
	private String name;
	
	private Customer customer;
	
	private List<Pizza> pizzas;
	
	public Order() {
		//id = (int)new Date().getTime();
		name = "" + stId++;
	}

	public Order(Customer customer, List<Pizza> pizzas) {
		this.customer = customer;
		this.pizzas = pizzas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name = " + name + ", customer=" + customer + ", pizzas="
				+ pizzas + "]";
	}
	
	public void destroy() {
		System.out.println("---destroy ");
	}
	
}
