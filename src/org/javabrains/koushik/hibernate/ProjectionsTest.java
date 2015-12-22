package org.javabrains.koushik.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class ProjectionsTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(UserDetailsSimplified.class)
							.setProjection(Projections.property("userId"))
							.addOrder(Order.desc("userId"));
		
		List<Integer> ids = criteria.list();
		
		session.getTransaction().commit();
		session.close();		
		
		for (Integer id : ids) {
			System.out.println(id);
		}
		
	}

}


/*
 *  Projections: tienen la utilidad de poder devolver las properties deseadas, ahorrando la necesidad de levantar toda la tabla
 * 				 tambinen se puede utilizar para que devuelva los resultados de funciones sql como count, max
 * 
 * 
 *  List<Integer> ids = criteria.list(); 
 *  
 *  Notar que en la linea de arriba la lista es una lista de enteros 
 *  y ya no es mas una lista de la clase UserDetailsSimplified como en el caso de ejemplo de la clase CriteriaTest,
 *  justamente porque se proyecta una propiedad / columna de la tabla 
 * 
 */