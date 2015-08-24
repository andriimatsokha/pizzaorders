package ua.pp.kaeltas.pizzaorders.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private String street;
	
	private String houseNumber;
	
	/*@OneToMany(mappedBy="address", cascade={CascadeType.ALL})
	private List<Order> orders;*/
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", houseNumber="
				+ houseNumber + "]";
	}

	
	
}
