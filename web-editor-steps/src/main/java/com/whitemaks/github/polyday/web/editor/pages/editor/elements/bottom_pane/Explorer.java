package com.whitemaks.github.polyday.web.editor.pages.editor.elements.bottom_pane;

import com.whitemaks.github.polyday.web.editor.pages.editor.EditorPage;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.ContextMenu;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.EditorConst;
import com.whitemaks.github.polyday.web.editor.utils.Common;
import com.whitemaks.github.web_driver.BaseWebElement;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

public class Explorer extends BaseWebElement {

	public Explorer(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	@Step("Вызов контекс меню в проводнике")
	public ContextMenu onContextMenu() {
		Assertions.assertTrue(webDriver.contextMenu(webElement), "Вызов контекст меню дожен быть успешен");
		return EditorPage.findContextMenuOnPage(webDriver);
	}

	@Step("Ожидание до тех пор пока не появится сущность с именем [ {name} ]")
	public Optional<WebElement> waitWhileEntityByNameWillBePresent(String name, int millis) {
		Optional<WebElement> result = Optional.empty();

		var currentMillis = 0;
		var stepCheck = 100;

		while (millis > currentMillis) {
			Common.sleep(stepCheck);
			currentMillis += stepCheck;

			result = getEntityByName(name);
			if (result.isPresent()) {
				return result;
			}
		}

		return result;
	}

	@Step("Получение списка элементов сущностей")
	public List<WebElement> getEntities() {
		return webElement.findElementsByClassName(EditorConst.CLASS_ACTIONS_ENTITY_OBJECT);
	}

	@Step("Получение элемента сущности по названию [ {name} ]")
	public Optional<WebElement> getEntityByName(String name) {
		return getEntities().stream()
				.filter(entity -> entity.getText().equals(name))
				.findFirst();
	}
}
