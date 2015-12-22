package org.javabrains.koushik.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushik.dto.UserDetailsSimplified;

public class HQLTest {
	
	public static void main(String[] args) {
	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// se utiliza el nombre de la clase como si fuera la tabla
		// y la variable de instancia como si fuera la columna
		Query query = session.createQuery("from UserDetailsSimplified where userId > 5");
		
		// paginacion
		// lo ideal seria tener la query en un metodo que recibe como parametros el offset y el limit
		
		query.setFirstResult(2); // le especifica a hibernate el offset, es decir que en este caso, el "resultSet" comienza a partir del 2do row 		
		query.setMaxResults(2); // especifica la cantidad maxima de rows que deben traerse, de fondo se transforma en la clausula limit de sql
		
		List<UserDetailsSimplified> users = (List<UserDetailsSimplified>) query.list();
		
		session.getTransaction().commit();
		session.close();
		System.out.println("Size of list result = " + users.size());
		
		for (UserDetailsSimplified userDetailsSimplified : users) {
			System.out.println(userDetailsSimplified.getUserName());
		}
	
	}

}

/*
 *  HQL por default, pullea los rows completos, es decir, todas las columnas asociadas al registro en cuestion
 *  Esto ocurriria por ejemplo con la siguiente query:
 *    
 *  session.createQuery("from UserDetailsSimplified")
 * 
 *  Si quisiera pullear especificamente algo y no todo del row hacemos lo siguiente:
 *  
 *  session.createQuery("select userName from UserDetailsSimplified")
 *  
 *  tener en consideracion que esto devolveria una lista de Strings y no una lista de objectos UserDetailsSimplified 
 *  como en el caso anterior 
 *  
 *  
 *  Tambien se puede hacer algo como esto:
 *  
 *  session.createQuery("select new map(userId, userName) from UserDetailsSimplified")
 *  
 *  devuelve una lista de maps
 *  
 *  
 *  Parameter binding / parameter substitution (para prevenir SQL injection)
 *  ------------------------------------------
 *  
 *  Al igual que en JDBC, lo ideal es armar las queries con los placeholders posicionales difinidos por: "?"
 *  Tambien se pueden usar los placeholders por nombre: ":NOMBRE"
 *  
 *  
 *  Named Queries
 *  -------------
 *  
 *  Se pueden ejecutar quries declaradas en annotations
 *  
 *  session.getNamedQuery("UserDetails.byId")    // ver la annotation en la clase UserDetailsSimplified
 *  query.setInteger(0, 2)
 *  
 */
