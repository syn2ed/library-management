package org.formation.gestionbiblio.model.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JTextField;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7551105475453015430L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private int role;
	
	@Column(name = "isValidate")
	private boolean isValidate;
	
	@Column(name = "email")
	private String email;
	
	public User() {
	}
	
	public User(String tf_id, String tf_password, String tf_email) {
		this.email = tf_email;
		this.password = tf_password;
		this.username = tf_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public boolean isValidate() {
		return isValidate;
	}
	public void setValidate(boolean validate) {
		this.isValidate = validate;
	}
}
