package com.whitemaks.github.polyday.web.editor.steps;

import com.whitemaks.github.polyday.db.developer_service.steps.DeveloperServiceDatabaseSteps;
import com.whitemaks.github.polyday.web.editor.utils.User;
import com.whitemaks.github.web_driver.WebDriverFactory;
import com.whitemaks.github.web_driver.wrappers.WebDriver;

public class EditorSteps {
	private final User user;
	private final WebDriver webDriver;
	private final EditorPageSteps editorPageSteps;
	private final DeveloperServiceDatabaseSteps developerServiceDatabaseSteps;

	public EditorSteps() {
		user = User.random();
		webDriver = WebDriverFactory.createChromeDriver();

		editorPageSteps = initEditorPageSteps();
		developerServiceDatabaseSteps = new DeveloperServiceDatabaseSteps();
	}

	public EditorPageSteps getEditorPageSteps() {
		return editorPageSteps;
	}

	public void cleanupReleases() {
		developerServiceDatabaseSteps.cleanupReleases();
	}

	public void cleanupBrowser() {
		WebDriverFactory.eliminateWebDriver();
	}

	private EditorPageSteps initEditorPageSteps() {
		var authPageSteps = new AuthenticatePageSteps(webDriver);
		authPageSteps.register(user);
		return authPageSteps.login(user);
	}

	public DeveloperServiceDatabaseSteps getDeveloperServiceDatabaseSteps() {
		return developerServiceDatabaseSteps;
	}
}
