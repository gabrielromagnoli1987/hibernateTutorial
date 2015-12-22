package org.javabrains.koushik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class TransientPersistentDetachedTest {
	
	/*
	 * 	Tutorial 22
	 *  Ver el archivo "algunos_conceptos.txt"
	 * 
	 *  El tutorial 23 explica estos conceptos mediante diagramas de estado
	 *  
	 * */

	public static void main(String[] args) {
		
		// Transient object
		UserDetailsSimplified user = new UserDetailsSimplified();
		user.setUserName("Test user");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(user);
		// En este punto el objeto user pasa a ser un persistent object
		
		user.setUserName("Updated user"); // dispararia una consulta update pero al haber otra despues, esta linea no genera la query
		user.setUserName("Updated user again"); // dispara una consulta update
		
		session.getTransaction().commit();
		session.close();
		
		// Detached object
		user.setUserName("Updated user after session close");

	}

}
