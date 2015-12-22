package org.javabrains.koushik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class CRUDTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		// CREATE - crea 10 objetos y los guarda
//		for (int i = 0; i < 10; i++) {
//			UserDetailsSimplified user = new UserDetailsSimplified();
//			user.setUserName("User " + i);
//			session.save(user);
//		}
		
		// READ - trae de la base al user con id 6
		UserDetailsSimplified user = (UserDetailsSimplified) session.get(UserDetailsSimplified.class, 6);
		System.out.println("User name pulled up is: " + user.getUserName());
		
		// UPDATE
		user.setUserName("Updated user");
		session.update(user);
		System.out.println("User name pulled up is: " + user.getUserName());
		
		// DELETE
		session.delete(user);
		
		session.getTransaction().commit();
		session.close();

	}

}
