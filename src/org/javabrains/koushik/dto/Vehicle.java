package org.javabrains.koushik.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // define la estrategia con la que se implementa la herencia en hibernate
public class Vehicle {
	
	@Id @GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
//	@ManyToOne
//	private UserDetails user;
	
	@ManyToMany(mappedBy="vehicles") // especificamos que el mapeo se realiza en la coleccion vehicles definida en UserDetails 
	private Collection<UserDetails> userList = new ArrayList<UserDetails>();
	
	
	public Collection<UserDetails> getUserList() {
		return userList;
	}
	public void setUserList(Collection<UserDetails> userList) {
		this.userList = userList;
	}
//	public UserDetails getUser() {
//		return user;
//	}
//	public void setUser(UserDetails user) {
//		this.user = user;
//	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

}
