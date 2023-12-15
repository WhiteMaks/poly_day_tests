package com.whitemaks.github.polyday.db.developer_service.dao;

import com.whitemaks.github.polyday.db.developer_service.domain.Entity;
import io.qameta.allure.Step;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EntityDao {
	private final SessionFactory sessionFactory;

	public EntityDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Step("Поиск сущности по name [ {name} ] и type [ {type} ]")
	public Entity findByNameAndType(String name, String type) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery("FROM Entity WHERE name = :name AND type = :type", Entity.class);
			query.setParameter("name", name);
			query.setParameter("type", type);
			return query.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Поиск сущностей по содержанию name [ {name} ]")
	public List<Entity> findByNameContains(String name) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery("FROM Entity WHERE name like :name", Entity.class);
			query.setParameter("name", name);
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Удаление сущностей [ {entities} ]")
	public void delete(List<Entity> entities) {
		Transaction transaction = null;

		try (var session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			for (var entity : entities) {
				session.remove(entity);
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
