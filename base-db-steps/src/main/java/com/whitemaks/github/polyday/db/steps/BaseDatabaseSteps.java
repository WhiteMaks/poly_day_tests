package com.whitemaks.github.polyday.db.steps;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public abstract class BaseDatabaseSteps {
	protected final SessionFactory sessionFactory;

	protected BaseDatabaseSteps(String host, String database, String username, String password, Class... annotatedClasses) {
		sessionFactory = getSessionFactory(
				host,
				database,
				username,
				password,
				annotatedClasses
		);
	}

	private SessionFactory getSessionFactory(String host, String database, String username, String password, Class... annotatedClasses) {
		try {
			var settings = new Properties();
			settings.put(Environment.DRIVER, "org.postgresql.Driver");
			settings.put(Environment.URL, "jdbc:postgresql://" + host + "/" + database);
			settings.put(Environment.USER, username);
			settings.put(Environment.PASS, password);
			settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

			var configuration = new Configuration();
			configuration.setProperties(settings);

			for (Class clazz : annotatedClasses) {
				configuration.addAnnotatedClass(clazz);
			}

			var serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
