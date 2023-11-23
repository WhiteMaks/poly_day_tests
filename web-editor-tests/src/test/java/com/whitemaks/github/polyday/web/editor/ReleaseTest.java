package com.whitemaks.github.polyday.web.editor;

import com.whitemaks.github.polyday.web.editor.steps.EditorSteps;
import com.whitemaks.github.polyday.web.editor.utils.Release;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Editor")
@Feature("Release")
public class ReleaseTest {
	private static final EditorSteps editorSteps = new EditorSteps();

	@Test
	@DisplayName("Создание нового релиза")
	@Description("Создание релиза с рандомным названием. Проверка его наличия на странице, а также в базе данных")
	public void createRelease() {
		var release = Release.random();

		var editorPageSteps = editorSteps.getEditorPageSteps();
		var developerServiceDbSteps = editorSteps.getDeveloperServiceDatabaseSteps();

		editorPageSteps.createRelease(release);
		developerServiceDbSteps.releaseByNameShouldBe(release.getName());
	}

	@Test
	@DisplayName("Удаление существующего релиза")
	@Description("Создание релиза в базе данных с рандомным названием, удаление созданного релиза в редакторе. Проверка его отсутствия на стринице, а так же в базе данных")
	public void deleteRelease() {
		var release = Release.random();

		var editorPageSteps = editorSteps.getEditorPageSteps();
		var developerServiceDbSteps = editorSteps.getDeveloperServiceDatabaseSteps();

		developerServiceDbSteps.createRelease(release.getName());
		editorPageSteps.refreshPage();

		editorPageSteps.deleteRelease(release);
		developerServiceDbSteps.releaseByNameShouldNotBe(release.getName());
	}

	@AfterAll
	public static void cleanup() {
		editorSteps.cleanupBrowser();
		editorSteps.cleanupReleases();
	}
}
