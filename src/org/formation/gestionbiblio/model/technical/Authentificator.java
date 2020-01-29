package org.formation.gestionbiblio.model.technical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
	private List<User> users;
	private Boolean isAuthenticated = false;
	private Boolean isValidated = false;
	private User userAuthentified;
	
	public Authentificator() throws FileNotFoundException {
		this.users = BiblioController.getInstance().getDbUsers();
	}

	public List<Boolean> checkUser(String username, String password) {
		List<Boolean> res = new ArrayList<Boolean>();
		
		for (User user : this.users) {
			if(user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				this.isAuthenticated = true;
				this.userAuthentified = user;
				if(!user.isValidate()) {
					this.isValidated = false;
				} else {
					this.isValidated = true;
				}
			}
		}
		
		res.add(isAuthenticated);
		res.add(isValidated);
		
		return res;
	}

	public User getUserAuthentified() {
		return userAuthentified;
	}

	public void refreshUsers() {
		this.users = BiblioController.getInstance().getDbUsers();
	}
}
