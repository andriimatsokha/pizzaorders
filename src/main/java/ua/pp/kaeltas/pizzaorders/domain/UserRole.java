package ua.pp.kaeltas.pizzaorders.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer; 
	
}
