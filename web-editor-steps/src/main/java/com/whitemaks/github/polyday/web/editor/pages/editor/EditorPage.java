package com.whitemaks.github.polyday.web.editor.pages.editor;

import com.whitemaks.github.polyday.web.editor.pages.editor.elements.EditorConst;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.TopPane;
import com.whitemaks.github.polyday.web.editor.utils.EnvironmentVariables;
import com.whitemaks.github.web_driver.BaseWebPage;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import io.qameta.allure.Step;

public class EditorPage extends BaseWebPage {

	public EditorPage(WebDriver webDriver) {
		super(webDriver);

		webDriver.openUrl(EnvironmentVariables.WEB_EDITOR_HOST);
	}

	@Step("Получение объекта верхней панели")
	public TopPane getTopPane() {
		return new TopPane(
				webDriver,
				getRootElement().findElementByClassName(EditorConst.CLASS_TOP_PANE_WRAPPER)
		);
	}
}
