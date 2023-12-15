package com.whitemaks.github.polyday.web.editor.utils;

import com.google.gson.Gson;

public class Entity {
	private final Type type;
	private final String name;

	public Entity(String name, Type type) {
		this.name= name;
		this.type = type;
	}

	public static Entity random(Type type) {
		return new Entity("Test " + RandomGenerator.randomString(5), type);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public Type getType() {
		return type;
	}

	public enum Type {
		FOLDER,
		MODEL,
		SHADER,
		SCRIPT,
		SOUND,
		MUSIC
	}
}
