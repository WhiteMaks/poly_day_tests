package com.whitemaks.github.polyday.db.developer_service.steps;

import com.whitemaks.github.polyday.db.developer_service.dao.EntityDao;
import com.whitemaks.github.polyday.db.developer_service.dao.ModelSourceDao;
import com.whitemaks.github.polyday.db.developer_service.dao.ReleaseDao;
import com.whitemaks.github.polyday.db.developer_service.dao.ShaderSourceDao;
import com.whitemaks.github.polyday.db.developer_service.domain.Entity;
import com.whitemaks.github.polyday.db.developer_service.domain.ModelSource;
import com.whitemaks.github.polyday.db.developer_service.domain.Release;
import com.whitemaks.github.polyday.db.developer_service.domain.ShaderSource;
import com.whitemaks.github.polyday.db.developer_service.utils.EnvironmentVariables;
import com.whitemaks.github.polyday.db.steps.BaseDatabaseSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class DeveloperServiceDatabaseSteps extends BaseDatabaseSteps {
	private final ReleaseDao releaseDao;
	private final EntityDao entityDao;
	private final ModelSourceDao modelSourceDao;
	private final ShaderSourceDao shaderSourceDao;

	public DeveloperServiceDatabaseSteps() {
		super(
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_HOST,
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_NAME,
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_USERNAME,
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_PASSWORD,
				Release.class,
				Entity.class,
				ModelSource.class,
				ShaderSource.class
		);

		releaseDao = new ReleaseDao(sessionFactory);
		entityDao = new EntityDao(sessionFactory);
		modelSourceDao = new ModelSourceDao(sessionFactory);
		shaderSourceDao = new ShaderSourceDao(sessionFactory);
	}

	@Step("Отчистка тестовых данных (релизов) в базе данных")
	public void cleanupReleases() {
		releaseDao.delete(
				releaseDao.findByNameContains("%Test%")
		);
	}

	@Step("Отчистка тестовых данных (сущностей) в базе данных")
	public void cleanupEntities() {
		var entities = entityDao.findByNameContains("%Test%");

		for (var entity : entities) {
			switch (entity.getType()) {
				case "MODEL" -> modelSourceDao.delete(modelSourceDao.findByEntityId(entity.getId()));
				case "SHADER" -> shaderSourceDao.delete(shaderSourceDao.findByEntityId(entity.getId()));
			}
		}

		entityDao.delete(entities);
	}

	@Step("Создание релиза в базе данных с названием [ {name} ]")
	public void createRelease(String name) {
		releaseDao.add(name, 1);
		releaseByNameShouldBe(name);
	}

	@Step("Проверка отсутствия релиза с названием [ {name} ] в базе данных")
	public void releaseByNameShouldNotBe(String name) {
		var dbRelease = releaseDao.findByName(name);
		Assertions.assertNull(dbRelease, "Релиза с именем [ " + name + " ] в базе данных существует");
	}

	@Step("Проверка наличия релиза с названием [ {name} ] в базе данных")
	public void releaseByNameShouldBe(String name) {
		var dbRelease = releaseDao.findByName(name);
		Assertions.assertNotNull(dbRelease, "Релиза с именем [ " + name + " ] в базе данных нет");
		Assertions.assertNotNull(dbRelease.getId(), "Поле Id должно быть заполнен");
		Assertions.assertNotNull(dbRelease.getUserId(), "Поле UserId должно быть заполнен");
		Assertions.assertNotNull(dbRelease.getCreatedAt(), "Поле CreatedAt должно быть заполнен");
		Assertions.assertNotNull(dbRelease.getUpdatedAt(), "Поле UpdatedAt должно быть заполнен");
		Assertions.assertEquals(name, dbRelease.getName(), "Поле Name должно быть равно ожидаемому");
	}

	@Step("Проверка наличия сущности с названием [ {name} ] и типом [ {type} ] в базе данных")
	public void entityByNameAndTypeShouldBe(String name, String type) {
		var dbEntity = entityDao.findByNameAndType(name, type);
		Assertions.assertNotNull(dbEntity, "Сущности с именем [ " + name + " ] и типом [ " + type + " ] в базе данных нет");
		Assertions.assertNotNull(dbEntity.getId(), "Поле Id должно быть заполнен");
		Assertions.assertNotNull(dbEntity.getUserId(), "Поле UserId должно быть заполнен");
		Assertions.assertNotNull(dbEntity.getCreatedAt(), "Поле CreatedAt должно быть заполнен");
		Assertions.assertNotNull(dbEntity.getUpdatedAt(), "Поле UpdatedAt должно быть заполнен");
		Assertions.assertEquals(name, dbEntity.getName(), "Поле Name должно быть равно ожидаемому");
		Assertions.assertEquals(type, dbEntity.getType(), "Поле Type должно быть равно ожидаемому");
		Assertions.assertNotNull(dbEntity.getParentEntityId(), "Поле ParentEntityId должно быть заполнен");
	}

	public ReleaseDao getReleaseDao() {
		return releaseDao;
	}

	public EntityDao getEntityDao() {
		return entityDao;
	}

	public ModelSourceDao getModelSourceDao() {
		return modelSourceDao;
	}

	public ShaderSourceDao getShaderSourceDao() {
		return shaderSourceDao;
	}
}
