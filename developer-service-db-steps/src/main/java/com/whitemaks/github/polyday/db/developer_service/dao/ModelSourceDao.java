package com.whitemaks.github.polyday.db.developer_service.dao;

import com.whitemaks.github.polyday.db.developer_service.domain.ModelSource;
import io.qameta.allure.Step;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ModelSourceDao {
	private final SessionFactory sessionFactory;

	public ModelSourceDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Step("Поиск источника модели по entityId [ {entityId} ]")
	public ModelSource findByEntityId(long entityId) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery("FROM ModelSource WHERE entityId = :entityId", ModelSource.class);
			query.setParameter("entityId", entityId);
			return query.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Удаление источника модели [ {modelSource} ]")
	public void delete(ModelSource modelSource) {
		delete(List.of(modelSource));
	}

	@Step("Удаление источника моделей [ {modelSources} ]")
	public void delete(List<ModelSource> modelSources) {
		Transaction transaction = null;

		try (var session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			for (var modelSource : modelSources) {
				session.remove(modelSource);
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
