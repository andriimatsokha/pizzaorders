package ua.pp.kaeltas.pizzaorders.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="orders")
@Component
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	private Date createDate = new Date();
	
	//private List<Pizza> pizzas;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="ORDER_PIZZA")
	@MapKeyJoinColumn(name="pizza_id")
	@Column(name="count")
	private Map<Pizza, Integer> pizzas;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	public Order() {
	}

	public Order(Customer customer, Map<Pizza, Integer> pizzas) {
		this.customer = customer;
		this.pizzas = pizzas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Pizza, Integer> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Pizza, Integer> pizzas) {
		this.pizzas = pizzas;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
//		if (!customer.getOrders().contains(this)) {
//			customer.getOrders().add(this);
//		}
		this.customer = customer;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzas="
				+ pizzas + "]";
	}
	
	public void destroy() {
		System.out.println("---destroy ");
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
