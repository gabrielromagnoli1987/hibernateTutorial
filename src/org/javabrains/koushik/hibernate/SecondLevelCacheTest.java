package org.javabrains.koushik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class SecondLevelCacheTest {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// primer select
		UserDetailsSimplified user = (UserDetailsSimplified) session.get(UserDetailsSimplified.class, 1);

		session.getTransaction().commit();
		session.close();
		
		
		// cerramos y volvemos a abrir sesion para entrar en el escenario de second level cache
		
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		
		// segundo select (el mismo no se ejecuta dado que gracias a la cache de segundo nivel, el objeto ya )
		// el objeto user2 sera actualizado desde la cache y va a ser igual a user
		
		UserDetailsSimplified user2 = (UserDetailsSimplified) session2.get(UserDetailsSimplified.class, 1);
		
		session2.getTransaction().commit();
		session2.close();

	}

}
