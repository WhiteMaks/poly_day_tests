package com.whitemaks.github.polyday.db.developer_service.steps;

import com.whitemaks.github.polyday.db.developer_service.dao.ReleaseDao;
import com.whitemaks.github.polyday.db.developer_service.domain.Release;
import com.whitemaks.github.polyday.db.developer_service.utils.EnvironmentVariables;
import com.whitemaks.github.polyday.db.steps.BaseDatabaseSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class DeveloperServiceDatabaseSteps extends BaseDatabaseSteps {
	private final ReleaseDao releaseDao;

	public DeveloperServiceDatabaseSteps() {
		super(
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_HOST,
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_NAME,
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_USERNAME,
				EnvironmentVariables.DEVELOPER_SERVICE_DATABASE_PASSWORD,
				Release.class
		);

		releaseDao = new ReleaseDao(sessionFactory);
	}

	@Step("Отчистка тестовых данных (релизов) в базе данных")
	public void cleanupReleases() {
		releaseDao.delete(
				releaseDao.findByNameContains("%Test%")
		);
	}

	@Step("Проверка наличия релиза с названием [ {name} ] в базе данных")
	public void releaseByNameMustBe(String name) {
		var dbRelease = releaseDao.findByName(name);
		Assertions.assertNotNull(dbRelease, "Релиза с именем [ " + name + " ] в базе данных нет");
		Assertions.assertNotNull(dbRelease.getId(), "Поле Id должно быть заполнен");
		Assertions.assertNotNull(dbRelease.getUserId(), "Поле UserId должно быть заполнен");
		Assertions.assertNotNull(dbRelease.getCreatedAt(), "Поле CreatedAt должно быть заполнен");
		Assertions.assertNotNull(dbRelease.getUpdatedAt(), "Поле UpdatedAt должно быть заполнен");
		Assertions.assertEquals(name, dbRelease.getName(), "Поле Name должно быть равно ожидаемому");
	}

	public ReleaseDao getReleaseDao() {
		return releaseDao;
	}
}
