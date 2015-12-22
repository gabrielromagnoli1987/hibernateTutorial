package org.javabrains.koushik.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class QueryCacheTest {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// primer query
		Query query = session.createQuery("from UserDetailsSimplified user where user.userId = 1"); // valor hardcodeado para simplificar el ejemplo
		query.setCacheable(true);
		
		List<UserDetailsSimplified> users = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		
		// cerramos y volvemos a abrir sesion para entrar en el escenario de second level cache
		
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		
		// segundo query
		Query query2 = session2.createQuery("from UserDetailsSimplified user where user.userId = 1");
		
		// es necesario setear que la query sea cacheable también en este punto para hacerle saber a hibernate
		// que primero debe revisar la cache
		
		query2.setCacheable(true);
		
		users = query2.list();
		
		session2.getTransaction().commit();
		session2.close();

	}

}
