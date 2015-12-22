package org.javabrains.koushik.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class QueryByExampleTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetailsSimplified exampleUser = new UserDetailsSimplified();
		exampleUser.setUserId(5);
		exampleUser.setUserName("User 5");
		
		Example example = Example.create(exampleUser);
		
		Criteria criteria = session.createCriteria(UserDetailsSimplified.class).add(example);
		
		List<UserDetailsSimplified> users = (List<UserDetailsSimplified>) criteria.list();
		
		session.getTransaction().commit();
		session.close();
		
		for (UserDetailsSimplified userDetailsSimplified : users) {
			System.out.println(userDetailsSimplified.getUserName());
		}

	}

}

/*
 *  El concepto de Query By Example surge de los casos en los que habria que utilizar muchos add(Restrictions...)
 *  y se torna engorroso el codigo.
 *  
 *  La idea es: se crea un objeto ejemplo especificando las properties del mismo y se lo pasa al criteria "diciendo"
 *  realizá la consulta devolviéndome algo como lo que te pasé.
 *  
 *  Hay que considerar que hibernate ignora dos cosas a la hora de usar esta metodología:
 *  
 *  las Primary Keys no serán tenidas en cuenta 
 *  si una property tiene el valor null no será considerada
 *  
 *  Si quisiera excluir alguna property para que no sea tenida en cuenta en la consulta se puede hacer lo siguiente:
 *  
 *  Example.create(exampleUser).excludeProperty("userName");
 *  
 *  Para poder utilizar lo que sería el LIKE de SQL se puede armar lo siguiente:
 *  
 *  exampleUser.setUserName("User 1%");  <-- Notar el %
 *  Example.create(exampleUser).enableLike();
 *  
 *  
 */
