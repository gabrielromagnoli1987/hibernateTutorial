package org.javabrains.koushik.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity // le especifica a hibernate que la clase puede ser leida como una entidad
public class UserDetails {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) // primary key - // por default la estrategia utilizada para generar los valores de los id es automatica
	private int UserId;
	
	//@Column (name="USER_NAME") se puede especificar el nombre de la columna, por default toma el nombre de la variable
	//@Basic ()
	//@Transient sirve para skipear en hibernate, no se agrega en la tabla.
	private String userName;
	
	@Temporal (TemporalType.DATE) // se le especifica a hibernate que se guarde solo la fecha, por default se guarda el timestamp
	private Date joinedDate;
	
	//private Address address;
	
	@Lob // large object, espcifica a hibernate que en la BD se utilizara una columna SIN restriccion de cantidad, por ej varchar(255) 
	private String description;
	
	//@ElementCollection // especifica que esta coleccion sera guardada en otra tabla a nivel base de datos y se creara la asociacion entre las dos tablas
					   // el nombre de la tabla queda: UserDetails_listOfAddresses  (NOMBRE DE LA CLASE_NOMBRE DEL ATRIBUTO)
	
//	@JoinTable(name="USER_ADDRESS" , // esta annotation NO es obligatoria, en este ejemplo es utilizada para cambiarle el nombre a la tabla
//		joinColumns=@JoinColumn(name="USER_ID") // de esta forma se modifica la primer columna, en este caso la variable UserId se renombra en la tabla
//	)
	//@GenericGenerator(name="hile-gen", strategy="hilo") // no JPA
	//@CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "hilo-gen", type = @Type(type="long"))  // annotation especifica de hibernate, no pertenece a JPA. La primary key sera de tipo long
	
	@OneToMany(cascade=CascadeType.ALL) // especifica que la lista de direcciones se va a guardar automaticamente cuando se guarde la instancia de UserDetails
	private Collection<Address> listOfAddresses = new ArrayList<Address>(); // arrayList soporta indices
	
//	@OneToOne
//	private Vehicle vehicle;
	
	//@OneToMany
	@ManyToMany(cascade=CascadeType.ALL)
	private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
//	public void setListOfAddresses(Set<Address> listOfAddresses) {
//		this.listOfAddresses = listOfAddresses;
//	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	public Vehicle getVehicle() {
//		return vehicle;
//	}
//	public void setVehicle(Vehicle vehicle) {
//		this.vehicle = vehicle;
//	}
	
	/*
	 * Existen annotations para sobreescribir los nombres de las columnas en el caso de por ejemplo tener dos 
	 * clases address embebidas, lo cual generaría conflicto al crearse 2 columnas con el mismo nombre
	 * @AtributeOverrides y @AtributeOverride 
	 * 
	 * Para los raros casos donde se necesita usar como id a una clave compuesta se puede utilizar @EmbeddedId
	 * 
	 * */

}
