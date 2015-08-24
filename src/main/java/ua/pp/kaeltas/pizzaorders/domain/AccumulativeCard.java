package ua.pp.kaeltas.pizzaorders.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AccumulativeCard {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="address_id")
	private Address address;
	
	private Integer sumOfAllOrders = 0;
	
	@OneToOne(mappedBy="accumulativeCard")
	private Customer customer;
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getSumOfAllOrders() {
		return sumOfAllOrders;
	}

	public void setSumOfAllOrders(Integer sumOfAllOrders) {
		this.sumOfAllOrders = sumOfAllOrders;
	}
	
	public synchronized void incrementSumOfAllOrders(Integer sum) {
		this.sumOfAllOrders += sum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

}
