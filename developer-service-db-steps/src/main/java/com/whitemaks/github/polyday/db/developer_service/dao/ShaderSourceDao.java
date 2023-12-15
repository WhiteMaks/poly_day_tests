package com.whitemaks.github.polyday.db.developer_service.dao;

import com.whitemaks.github.polyday.db.developer_service.domain.ShaderSource;
import io.qameta.allure.Step;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ShaderSourceDao {
	private final SessionFactory sessionFactory;

	public ShaderSourceDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Step("Поиск источника шейдера по entityId [ {entityId} ]")
	public ShaderSource findByEntityId(long entityId) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery("FROM ShaderSource WHERE entityId = :entityId", ShaderSource.class);
			query.setParameter("entityId", entityId);
			return query.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Удаление источника шейдера [ {shaderSource} ]")
	public void delete(ShaderSource shaderSource) {
		delete(List.of(shaderSource));
	}

	@Step("Удаление источника шейдеров [ {shaderSources} ]")
	public void delete(List<ShaderSource> shaderSources) {
		Transaction transaction = null;

		try (var session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			for (var shaderSource : shaderSources) {
				session.remove(shaderSource);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}
}
