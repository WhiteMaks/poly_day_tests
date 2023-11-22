package com.whitemaks.github.polyday.db.developer_service.dao;

import com.whitemaks.github.polyday.db.developer_service.domain.Release;
import io.qameta.allure.Step;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReleaseDao {
	private final SessionFactory sessionFactory;

	public ReleaseDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Step("Поиск релиза по id [ {id} ]")
	public Release findById(Long id) {
		try (var session = sessionFactory.openSession()) {
			return session.get(Release.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Поиск релиза по name [ {name} ]")
	public Release findByName(String name) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery("FROM Release WHERE name = :name", Release.class);
			query.setParameter("name", name);
			return query.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Поиск релизов по содержанию name [ {name} ]")
	public List<Release> findByNameContains(String name) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createQuery("FROM Release WHERE name like :name", Release.class);
			query.setParameter("name", name);
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Step("Удаление релизов [ {releases} ]")
	public void delete(List<Release> releases) {
		Transaction transaction = null;

		try (var session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();

			for (var release : releases) {
				session.remove(release);
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
