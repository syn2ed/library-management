package org.formation.gestionbiblio.model.technical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.business.User;
import org.formation.gestionbiblio.utils.BiblioAppLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Authentificator {
	private String usersFilePath = "Users.json";
	private List<User> users;
	private Boolean isAuthenticated = false;
	private Boolean isValidated = false;
	private User userAuthentified;
	
	public Authentificator() throws FileNotFoundException {
		//this.users = this.parseUsers(usersFilePath);
		this.users = BiblioController.getInstance().getDbUsers();
	}

	private List<User> parseUsers(String usersFilePath) throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader(this.usersFilePath));
		Type type = new TypeToken<List<User>>(){}.getType();
		List<User> models = gson.fromJson(br, type);
		
		return models;
	}

	public boolean checkUser(String username, String password) {
		for (User user : this.users) {
			if(user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				if(!user.isValidate()) {
					this.isValidated = false;
				} else {
					this.isValidated = true;
					this.isAuthenticated = true;
					this.userAuthentified = user;
					return isAuthenticated;
				}
			}
		}
		return isAuthenticated;
	}

	public User getUserAuthentified() {
		return userAuthentified;
	}
	
}
