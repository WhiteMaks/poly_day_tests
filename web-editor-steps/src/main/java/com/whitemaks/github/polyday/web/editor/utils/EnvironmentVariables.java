package com.whitemaks.github.polyday.web.editor.utils;

public interface EnvironmentVariables {
	String WEB_EDITOR_HOST = System.getenv("WEB_EDITOR_HOST") != null
			? System.getenv("WEB_EDITOR_HOST") : "http://localhost:8888";
}
