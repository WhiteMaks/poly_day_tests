package com.whitemaks.github.polyday.web.editor.pages.editor.elements;

import com.whitemaks.github.web_driver.BaseWebElement;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;

public class Modal extends BaseWebElement {

	public Modal(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	@Step("Получение элемента кнопки для подтверждения создания")
	public WebElement getCreateButton() {
		return findButtonByName(EditorConst.NAME_CREATE);
	}

	@Step("Получение элемента кнопки для отмены")
	public WebElement getCancelButton() {
		return findButtonByName(EditorConst.NAME_CANCEL);
	}

	@Step("Получение элемента инпута для ввода названия")
	public WebElement getNameInput() {
		return webElement.findElementByClassName(EditorConst.CLASS_ACTION_INPUT);
	}

	@Step("Получение индикатора нахождения на дисплее для модального окна")
	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}
}
