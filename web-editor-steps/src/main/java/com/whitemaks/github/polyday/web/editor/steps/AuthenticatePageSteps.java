package com.whitemaks.github.polyday.web.editor.steps;

import com.whitemaks.github.polyday.web.editor.pages.authenticate.AuthenticatePage;
import com.whitemaks.github.polyday.web.editor.utils.Common;
import com.whitemaks.github.polyday.web.editor.utils.User;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import io.qameta.allure.Step;

public class AuthenticatePageSteps {
	private final WebDriver webDriver;
	private final AuthenticatePage authenticatePage;

	public AuthenticatePageSteps(WebDriver webDriver) {
		this.webDriver = webDriver;

		authenticatePage = new AuthenticatePage(webDriver);
	}

	@Step("Регистрация нового пользователя. Username = [ {user.username} ], password = [ {user.password} ]")
	public void register(User user) {
		var register = authenticatePage.goToRegister();
		var usernameInput = register.getUsernameInput();
		var passwordInput = register.getPasswordInput();
		var registerButton = register.getRegisterButton();

		usernameInput.clear();
		passwordInput.clear();

		usernameInput.sendKeys(user.getUsername());
		passwordInput.sendKeys(user.getPassword());
		registerButton.click();

		Common.sleep();
	}

	@Step("Авторизация пользователя. Username = [ {user.username} ], password = [ {user.password} ]")
	public EditorPageSteps login(User user) {
		var login = authenticatePage.goToLogin();
		var usernameInput = login.getUsernameInput();
		var passwordInput = login.getPasswordInput();
		var loginButton = login.getLoginButton();

		usernameInput.clear();
		passwordInput.clear();

		usernameInput.sendKeys(user.getUsername());
		passwordInput.sendKeys(user.getPassword());

		loginButton.click();

		Common.sleep();

		return new EditorPageSteps(webDriver);
	}
}
