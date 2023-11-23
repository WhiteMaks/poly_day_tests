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

	@Step("Удаление релиза с названием [ {release.name} ]")
	public void deleteRelease(Release release) {
		var topPane = editorPage.getTopPane();

		var contextMenu = topPane.contextMenuOnReleaseTab(release.getName());

		Assertions.assertTrue(contextMenu.isDisplayed(), "Контекст меню должно быть видно после нажатия по релизу");

		var deleteButton = contextMenu.getDeleteButton();
		var renameButton = contextMenu.getRenameButton();
		var releaseTabsBeforeDelete = topPane.getReleaseTabs();

		Allure.step("Проверка элементов контекст меню", () -> {
			Assertions.assertAll(() -> {
				Assertions.assertTrue(deleteButton.isFound(), "Кнопка удалния в контекст меню должна быть найдена");
				Assertions.assertTrue(deleteButton.isDisplayed(), "Кнопка удалния в контекст меню должна быть видна");

				Assertions.assertTrue(renameButton.isFound(), "Кнопка переименования в контекст меню должна быть найдена");
				Assertions.assertTrue(renameButton.isDisplayed(), "Кнопка переименования в контекст меню должна быть видна");
			});
		});

		Allure.step("Удаление релиза", () -> {
			Assertions.assertTrue(deleteButton.click(), "Нажатие по кнопке удаления в контекст меню должно быть успешным");
			Assertions.assertFalse(contextMenu.isDisplayed(), "Контекст меню должно быть скрыто после нажатия по кнопке удаления");
		});

		Allure.step("Проверка на отсутствие только что удаленного релиза в вехней панели", () -> {
			var releaseTabsAfterDelete = topPane.getReleaseTabs();
			Assertions.assertTrue(releaseTabsAfterDelete.size() <= releaseTabsBeforeDelete.size() - 1, "После закрытия контекс меню должно быть меньше релизов чем было до удаления");

			var deletedReleaseTabOptional = topPane.getReleaseTabByName(release.getName());
			Assertions.assertFalse(deletedReleaseTabOptional.isPresent(), "Только что удаленный релиз должен был пропасть среди релизов");
		});
	}

	@Step("Создание релиза с названием [ {release.name} ]")
	public void createRelease(Release release) {
		var topPane = editorPage.getTopPane();
		var modalCreateRelease = topPane.clickOnCreateReleaseButton();

		Assertions.assertTrue(modalCreateRelease.isDisplayed(), "Модальное окно должно быть видно после нажатия по кнопке создания релиза");

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

			var newReleaseTabOptional = topPane.getReleaseTabByName(release.getName());
			Assertions.assertTrue(newReleaseTabOptional.isPresent(), "Только что созданный релиз должен был появиться среди релизов");
		});
	}

	@Step("Обновление страницы")
	public void refreshPage() {
		editorPage.refreshPage();
	}
}
