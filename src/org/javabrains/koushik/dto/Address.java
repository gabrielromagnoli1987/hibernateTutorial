package org.javabrains.koushik.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/*
 *  una Entity tiene significado por si misma
 *  un valueObject NO tiene sifnificado por si mismo, se basa en otro objeto. 
 *  Se utiliza @Embeddable para que hibernate sepa que la clase va a ser tratada como una clase dependiente de otra
 *  Lo que genera esto a nivel base de datos es que en una misma tabla, en este caso la de UserDetails, se van a agregar
 *  las columnas pertenecientes a la clase Address.
 * */

//@Embeddable // 
@Entity
public class Address {
	
	@Id @GeneratedValue
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String street;
	private String city;
	private String state;
	private String pincode;
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
