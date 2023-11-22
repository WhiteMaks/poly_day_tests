package com.whitemaks.github.polyday.web.editor.utils;

import com.google.gson.Gson;

public class Release {
	private final String name;

	public Release(String name) {
		this.name= name;
	}

	public static Release random() {
		return new Release("Test " + RandomGenerator.randomString(5));
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
