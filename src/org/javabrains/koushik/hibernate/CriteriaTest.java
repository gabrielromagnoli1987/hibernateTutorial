package org.javabrains.koushik.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class CriteriaTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(UserDetailsSimplified.class); // sobre que clase se utiliza el criteria
		criteria.add(Restrictions.eq("userName", "User 1")); // La clase restriction simula el where. Los metodos de restriction son las condiciones
		
		criteria.add(Restrictions.or(Restrictions.between("userId", 0, 3), Restrictions.between("userId", 7, 10)));
		
		List<UserDetailsSimplified> users = (List<UserDetailsSimplified>) criteria.list();
		
		session.getTransaction().commit();
		session.close();
		System.out.println("Size of list result = " + users.size());
		
		for (UserDetailsSimplified userDetailsSimplified : users) {
			System.out.println(userDetailsSimplified.getUserName());
		}

	}

}


/*
 *  La utilizacion / necesidad de Criteria nace a partir de que HQL sigue siendo un lenguaje orientado a queries
 *  y esta "ligado" a SQL, por lo que la mantenibilidad de grandes queries es un problema. 
 * 
 *  Los criteria vendrian a ser como las clausulas where
 *  
 *  El metodo add devuelve un criteria, por lo que se pueden encadenar los add. Ej:
 *  criteria.add(Restrictions.eq("userName", "User 1")).add(...).add(...)
 *  
 *  Por default, al estar agregando criterias, la operacion utilizada en la consulta final es el AND.
 *  Para utilizar el OR, ejemplo:
 *  
 *  criteria.add(Restrictions.or(Restrictions.between(propertyName, lo, hi), Restriction.eq("propertyName", "value")));
 *  							 --------PRIMERA CONDICION------------------, ----------SEGUNDA CONDICION-------------
 *  
 */