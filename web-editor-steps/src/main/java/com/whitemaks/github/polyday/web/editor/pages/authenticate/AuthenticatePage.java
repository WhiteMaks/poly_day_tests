package com.whitemaks.github.polyday.web.editor.pages.authenticate;

import com.whitemaks.github.polyday.web.editor.pages.authenticate.elements.Login;
import com.whitemaks.github.polyday.web.editor.pages.authenticate.elements.Register;
import com.whitemaks.github.polyday.web.editor.utils.EnvironmentVariables;
import com.whitemaks.github.web_driver.BaseWebPage;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;

import java.util.List;

public class AuthenticatePage extends BaseWebPage {

	public AuthenticatePage(WebDriver webDriver) {
		super(webDriver);

		webDriver.openUrl(EnvironmentVariables.WEB_EDITOR_HOST);
	}

	@Step("Переход на авторизацию")
	public Login goToLogin() {
		var buttons = getButtons();
		if (buttons.get(buttons.size() - 1).getText().equalsIgnoreCase("login")) {
			buttons.get(buttons.size() - 1).click();
		}

		return new Login(webDriver, getRootElement());
	}

	@Step("Переход на регистрацию")
	public Register goToRegister() {
		var buttons = getButtons();
		if (buttons.get(buttons.size() - 1).getText().equalsIgnoreCase("register")) {
			buttons.get(buttons.size() - 1).click();
		}

		return new Register(webDriver, getRootElement());
	}

	private List<WebElement> getButtons() {
		return getRootElement()
				.findElementsByTagName("button");
	}
}
