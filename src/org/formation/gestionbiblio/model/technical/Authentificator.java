package org.formation.gestionbiblio.model.technical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;

import org.formation.gestionbiblio.model.business.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Authentificator {
	private String usersFilePath = "Users.json";
	private List<User> users;
	private Boolean isAuthenticated = false;
	private User userAuthentified;
	
	public Authentificator() throws FileNotFoundException {
		this.users = this.parseUsers(usersFilePath);
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
				this.isAuthenticated = true;
				this.userAuthentified = user;
				System.out.println(user.getRole());
				return isAuthenticated;
			}
		}
		return isAuthenticated;
	}
	
}
