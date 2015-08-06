package ua.pp.kaeltas.pizzaorders.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pizza {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private Integer price;
	
	@Enumerated(EnumType.STRING)
	private PizzaType type;
	
	public Pizza() {
	}

	public Pizza(int id, String name, int price, PizzaType type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public PizzaType getType() {
		return type;
	}
	
	public void setType(PizzaType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", price=" + price
				+ ", type=" + type + "]";
	}
	
	
}
