package com.example.todoapp.login;

import org.springframework.stereotype.Service;

@Service
public class Authenticationservice {
	public boolean authenticate (String username,String password) {
		boolean isValidUser = username.equalsIgnoreCase("Jeba Starlin");
		boolean isValidPassword = password.equalsIgnoreCase("1234567890");
		return isValidUser && isValidPassword;
	}
}
