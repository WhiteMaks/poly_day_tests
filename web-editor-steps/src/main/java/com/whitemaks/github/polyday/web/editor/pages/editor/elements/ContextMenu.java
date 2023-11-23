package com.whitemaks.github.polyday.web.editor.pages.editor.elements;

import com.whitemaks.github.web_driver.BaseWebElement;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;

public class ContextMenu extends BaseWebElement {

	public ContextMenu(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	@Step("Получение индикатора нахождения на дисплее для контекст меню")
	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	@Step("Получение элемента кнопки для удаления")
	public WebElement getDeleteButton() {
		return webElement.findElementByText(EditorConst.NAME_DELETE);
	}

	@Step("Получение элемента кнопки для переименованию")
	public WebElement getRenameButton() {
		return webElement.findElementByText(EditorConst.NAME_RENAME);
	}
}
