package org.formation.gestionbiblio.model.business;

public class User {
	private String username;
	private String password;
	private int role;
	private boolean isValidate;
	
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
