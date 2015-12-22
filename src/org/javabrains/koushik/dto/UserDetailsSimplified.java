package org.javabrains.koushik.dto;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="UserDetailsSimplified.byId", query="from UserDetailsSimplified where userId = ?") // query del tipo HQL
// para poder ejecutar queries nativas (tambien podria ser un stored procedure):
@NamedNativeQuery(name="UserDetailsSimplified.byName", query="select * from UserDetailsSimplified where username = ?", resultClass=UserDetails.class)
public class UserDetailsSimplified {
	
	/*
	 *  Esta clase es para el tutorial 21 - CRUD Operations 
	 *  
	 * */
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String userName;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
