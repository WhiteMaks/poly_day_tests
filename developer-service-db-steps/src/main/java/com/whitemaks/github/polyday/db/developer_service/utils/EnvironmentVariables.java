package com.whitemaks.github.polyday.db.developer_service.utils;

public interface EnvironmentVariables {
	String DEVELOPER_SERVICE_DATABASE_HOST = System.getenv("DEVELOPER_SERVICE_DATABASE_HOST") != null
			? System.getenv("DEVELOPER_SERVICE_DATABASE_HOST") : "localhost:25432";
	String DEVELOPER_SERVICE_DATABASE_NAME = System.getenv("DEVELOPER_SERVICE_DATABASE_NAME") != null
			? System.getenv("DEVELOPER_SERVICE_DATABASE_NAME") : "developer_service";
	String DEVELOPER_SERVICE_DATABASE_USERNAME = System.getenv("DEVELOPER_SERVICE_DATABASE_USERNAME") != null
			? System.getenv("DEVELOPER_SERVICE_DATABASE_USERNAME") : "user";
	String DEVELOPER_SERVICE_DATABASE_PASSWORD = System.getenv("DEVELOPER_SERVICE_DATABASE_PASSWORD") != null
			? System.getenv("DEVELOPER_SERVICE_DATABASE_PASSWORD") : "password";
}
