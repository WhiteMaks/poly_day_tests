package com.whitemaks.github.polyday.web.editor.pages.editor.elements;

import com.whitemaks.github.web_driver.BaseWebElement;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

public class TopPane extends BaseWebElement {

	public TopPane(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	@Step("Нажатие по кнопке создания релиза и получение объекта модального окна")
	public ModalCreateRelease clickOnCreateReleaseButton() {
		var button = getCreateReleaseButton();

		Allure.step("Проверка кнопки для создание релиза на доступность, после чего клик по кнопке", () -> {
			Assertions.assertTrue(button.isFound(), "Кнопка для создания релиза должна быть найдена");
			Assertions.assertTrue(button.isDisplayed(), "Кнопка для создания релиза должна быть видна");
			Assertions.assertTrue(button.isEnabled(), "Кнопка для создания релиза должна быть активна");
			Assertions.assertTrue(button.click(), "Клик по кнопке создания релиза должен быть успешным");
		});

		var modalElement = webDriver.getRootElement()
				.findElementByClassName(EditorConst.CLASS_MODAL_WRAPPER);

		Assertions.assertTrue(modalElement.isFound(), "После нажатия по кнопке создания релиза должно появиться модальное окно");

		return new ModalCreateRelease(webDriver, modalElement);
	}

	@Step("Получение элемента кнопки для создания релиза")
	public WebElement getCreateReleaseButton() {
		return findButtonByClassName(EditorConst.CLASS_ACTION_BASE_BUTTON);
	}

	@Step("Получение списка релизных элементов")
	public List<WebElement> getReleaseTabs() {
		return webElement.findElementsByClassName(EditorConst.CLASS_RELEASE_TAB_WRAPPER);
	}

	@Step("Получение релизного элемента по названию [ {name} ]")
	public Optional<WebElement> getReleaseTabByName(String name) {
		return getReleaseTabs().stream()
				.filter(releaseTab -> releaseTab.getText().equals(name))
				.findFirst();
	}

	@Step("Вызов контекс меню на релизном элементе с названием [ {name} ]")
	public ContextMenu contextMenuOnReleaseTab(String name) {
		var releaseTabOptional = getReleaseTabs().stream()
				.filter(releaseTab -> releaseTab.getText().equals(name))
				.findFirst();
		Assertions.assertTrue(releaseTabOptional.isPresent(), "Релиз [ " + name + " ] должен должен быть среди релизов");

		var releaseTab = releaseTabOptional.get();
		Assertions.assertTrue(webDriver.contextMenu(releaseTab), "Вызов контекст меню дожен быть успешен");

		var contextMenuWebElement = webDriver.getRootElement()
				.findElementByClassName(EditorConst.CLASS_ACTION_CONTEXT_MENU);
		Assertions.assertTrue(contextMenuWebElement.isFound(), "Элемент контекст меню должен быть найден");

		return new ContextMenu(webDriver, contextMenuWebElement);
	}
}
