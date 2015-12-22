package org.javabrains.koushik.hibernate;

import java.util.Date;

import javax.persistence.GeneratedValue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.Address;
import org.javabrains.koushik.dto.UserDetails;
import org.javabrains.koushik.dto.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		
		UserDetails user = new UserDetails();
		//user.setUserId(1); // al agregar @GeneratedValue en la clase del modelo, no hace falta setear el id
		user.setUserName("First User");
		user.setJoinedDate(new Date());
		user.setDescription("description");
		
		Address address = new Address();
		address.setStreet("street");
		address.setCity("city");
		address.setState("state");
		address.setPincode("100001");
		
		Address address2 = new Address();
		address2.setStreet("street 2");
		address2.setCity("city 2");
		address2.setState("state 2");
		address2.setPincode("200002");
		
		user.getListOfAddresses().add(address);
		user.getListOfAddresses().add(address2);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("car");
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");
		
		user.getVehicles().add(vehicle);
		user.getVehicles().add(vehicle2);
		vehicle.getUserList().add(user);
		vehicle2.getUserList().add(user);
		
//		
//		user.setVehicle(vehicle);
		
		//user.setAddress(address);
		
//		1. crear la session factory
//		2. crear un objeto session a partir de la session factory
//		3. con el objeto session grabar el objeto del modelo a la DB
		
		// crea una session factory a partir del archivo de configuracion hibernate.cfg.xml
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		
		// se puede usar un try catch para el manejo de errores
		
		Session session = sessionFactory.openSession();
		session.beginTransaction(); // se pueden guardar varios objetos en una transaction
//		session.save(vehicle);
//		session.save(vehicle2);
		session.save(user);
		session.getTransaction().commit();
		session.close(); // el cierre de sesion iria en un finally
		
		
		/*
		 * Hibernate fetch strategy:
		 * El tema de interes es, ¿Que pasa cuando hago un get de un usuario?, 
		 * Esa consulta, ¿Trae todas las direcciones (listOfAddresses) ? 
		 * 
		 * Por default, hibernate NO trae todas las tuplas asociadas.
		 * Se le puede especificar para que si lo haga.
		 * 
		 * */
		
		
//		user = null;
		
		// la session factory se crea una sola vez
//		session = sessionFactory.openSession();
//		session.beginTransaction();
//		user = (UserDetails) session.get(UserDetails.class, 1); // el segundo parametro es el id
		
		//session.close(); // si cerramos la sesion en este punto, nos devuelve una LazyInitializationException al querer obtener la listOfAddresses 
		
//		user.getListOfAddresses(); // al llamar a este metodo, hibernate ejecuta una query para traer todas las direcciones asociadas al user
		// este tipo de estrategia se llama Lazy initialization. La estrategia contraria se llama Eager initialization (o sea trae todo de una) 
		// ¿Como hace hibernate para darse cuanta en lazy initialization, que tiene que traer todos los registros si solamente estamos usando un metodo get?
		// Utilizando una proxy class. Es decir hibernate no te devuelve la clase final del modelo tuyo, en este caso UserDetails, sino que
		// te devuelve una ProxyUserDetails, que es una sub clase de UserDetails
		// Para configurar en modo Eager: @EelementCollection(fetch=FetchType.EAGER)
		
		
//		session.close();
//		System.out.println("User name retrived is " + user.getUserName());


	}

}
