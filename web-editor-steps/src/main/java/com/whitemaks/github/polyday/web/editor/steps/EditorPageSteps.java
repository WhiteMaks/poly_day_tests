package com.whitemaks.github.polyday.web.editor.steps;

import com.whitemaks.github.polyday.web.editor.pages.editor.EditorPage;
import com.whitemaks.github.polyday.web.editor.utils.Release;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class EditorPageSteps {
	private final EditorPage editorPage;

	public EditorPageSteps(WebDriver webDriver) {
		editorPage = new EditorPage(webDriver);
	}

	@Step("Создание релиза с названием [ {release.name} ]")
	public void createRelease(Release release) {
		var topPane = editorPage.getTopPane();
		var modalCreateRelease = topPane.clickOnCreateReleaseButton();

		Assertions.assertTrue(modalCreateRelease.isDisplayed(), "Модальное окна должно быть видно после нажатия по кнопке создания релиза");

		var createButtonInModal = modalCreateRelease.getCreateButton();
		var cancelButtonInModal = modalCreateRelease.getCancelButton();
		var releaseNameInput = modalCreateRelease.getReleaseNameInput();
		var releaseTabsBeforeCreate = topPane.getReleaseTabs();

		Allure.step("Проверка элементов модального окна", () -> {
			Assertions.assertAll(() -> {
				Assertions.assertTrue(createButtonInModal.isFound(), "Кнопка создания в модальном окне должна быть найдена");
				Assertions.assertTrue(createButtonInModal.isDisplayed(), "Кнопка создания в модальном окне должна быть видна");

				Assertions.assertTrue(cancelButtonInModal.isFound(), "Кнопка отмены в модальном окне должна быть найдена");
				Assertions.assertTrue(cancelButtonInModal.isDisplayed(), "Кнопка отмены в модальном окне должна быть видна");

				Assertions.assertTrue(releaseNameInput.isFound(), "Поле для имени релиза в модальном окне должен быть найден");
				Assertions.assertTrue(releaseNameInput.isDisplayed(), "Поле для имени релиза в модальном окне должен быть виден");
			});
		});

		Allure.step("Заполнение данных в инпут с название релиза", () -> {
			Assertions.assertTrue(releaseNameInput.sendKeys(release.getName()), "Ввод в поле для имени релиза в модальном окне дожно быть успешным");

			var actualReleaseName = releaseNameInput.getValue();
			Assertions.assertEquals(release.getName(), actualReleaseName, "Значение в поле для ввода имени релиза в модальном окне должно совпадать с введенным");
		});

		Allure.step("Подтверждение создания релиза", () -> {
			Assertions.assertTrue(createButtonInModal.click(), "Нажатие по кнопке создания релиза в модальном окне должно быть успешным");
			Assertions.assertFalse(modalCreateRelease.isDisplayed(), "Модальное окно должно быть скрыто после нажатия по кнопке создания релиза");
		});

		Allure.step("Проверка на наличие только что созданного релиза в вехней панели", () -> {
			var releaseTabsAfterCreate = topPane.getReleaseTabs();
			Assertions.assertTrue(releaseTabsAfterCreate.size() >= releaseTabsBeforeCreate.size() + 1, "После закрытия модального окна должно быть больше релизов чем было до создания нового");

			var newReleaseTabOptional = releaseTabsAfterCreate.stream()
					.filter(releaseTab -> releaseTab.getText().equals(release.getName()))
					.findFirst();
			Assertions.assertTrue(newReleaseTabOptional.isPresent(), "Только что созданный релиз должен был появиться среди релизов");
		});
	}
}
