package com.whitemaks.github.polyday.web.editor.utils;

public class User {
	private final String username;
	private final String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public static User random() {
		return new User(
			RandomGenerator.randomString(15),
			RandomGenerator.randomString(15)
		);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
