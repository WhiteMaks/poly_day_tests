package com.whitemaks.github.polyday.web.editor;

import com.whitemaks.github.polyday.web.editor.steps.EditorSteps;
import com.whitemaks.github.polyday.web.editor.utils.Entity;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Epic("Editor")
@Feature("Entity")
public class EntityTest {
	private static final EditorSteps editorSteps = new EditorSteps();

	private static Stream<Entity.Type> entityTypes() {
		return Stream.of(
				Entity.Type.FOLDER,
				Entity.Type.MODEL,
				Entity.Type.SHADER,
				Entity.Type.MUSIC,
				Entity.Type.SCRIPT,
				Entity.Type.SOUND
		);
	}

	@ParameterizedTest
	@MethodSource("entityTypes")
	@DisplayName("Создание новой сущности")
	@Description("Создание сущности с рандомным названием. Проверка её наличия на странице, а также в базе данных")
	public void createEntity(Entity.Type type) {
		var entity = Entity.random(type);

		var editorPageSteps = editorSteps.getEditorPageSteps();
		var developerServiceDbSteps = editorSteps.getDeveloperServiceDatabaseSteps();

		editorPageSteps.createEntity(entity);
		developerServiceDbSteps.entityByNameAndTypeShouldBe(entity.getName(), entity.getType().name());
	}

	@AfterAll
	public static void cleanup() {
		editorSteps.cleanupBrowser();
		editorSteps.cleanupEntities();
	}
}
