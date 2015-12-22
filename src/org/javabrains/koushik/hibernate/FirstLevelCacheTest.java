package org.javabrains.koushik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class FirstLevelCacheTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// primer select
		UserDetailsSimplified user = (UserDetailsSimplified) session.get(UserDetailsSimplified.class, 1);
		
		// genera un update
		user.setUserName("Updated user");
		
		// aca se debería llamar a otra consulta select y traer el objeto desde la BD
		// pero como se implementa por default el primer nivel de cache de hibernate
		// el objeto user ya se encuentra en la cache actualizado, entonces en vez de ir a buscar el objeto
		// a la BD, se lo obtiene de la cache
		UserDetailsSimplified user2 = (UserDetailsSimplified) session.get(UserDetailsSimplified.class, 1);
		
		System.out.println(user2.getUserName());
		
		session.getTransaction().commit();
		session.close();

	}

}
