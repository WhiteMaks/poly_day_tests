package com.whitemaks.github.polyday.web.editor.pages.authenticate.elements;

import com.whitemaks.github.web_driver.BaseWebElement;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;

public class Register extends BaseWebElement {

	public Register(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	@Step("Получение элемента инпута для ввода имени пользователя")
	public WebElement getUsernameInput() {
		return findInputs().get(0);
	}

	@Step("Получение элемента инпута для ввода пароля пользователя")
	public WebElement getPasswordInput() {
		return findInputs().get(1);
	}

	@Step("Получение элемента кнопки для подтверждения регистрации")
	public WebElement getRegisterButton() {
		return findButtonByName("Register");
	}

	@Step("Получение элемента кнопки для перехода на авторизацию")
	public WebElement getLoginButton() {
		return findButtonByName("Login");
	}
}
