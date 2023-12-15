package com.whitemaks.github.polyday.web.editor.pages.editor.elements.bottom_pane;

import com.whitemaks.github.polyday.web.editor.pages.editor.elements.EditorConst;
import com.whitemaks.github.web_driver.BaseWebElement;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;

public class BottomPane extends BaseWebElement {

	public BottomPane(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	@Step("Получение объекта проводника")
	public Explorer getExplorer() {
		return new Explorer(
				webDriver,
				webElement.findElementByClassName(EditorConst.CLASS_BOTTOM_PANE_EXPLORER)
		);
	}
}
