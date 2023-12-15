package com.whitemaks.github.polyday.web.editor.pages.editor;

import com.whitemaks.github.polyday.web.editor.pages.editor.elements.ContextMenu;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.Modal;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.bottom_pane.BottomPane;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.EditorConst;
import com.whitemaks.github.polyday.web.editor.pages.editor.elements.top_pane.TopPane;
import com.whitemaks.github.polyday.web.editor.utils.EnvironmentVariables;
import com.whitemaks.github.web_driver.BaseWebPage;
import com.whitemaks.github.web_driver.wrappers.WebDriver;
import com.whitemaks.github.web_driver.wrappers.WebElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

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

	@Step("Получение объекта нижней панели")
	public BottomPane getBottomPane() {
		return new BottomPane(
				webDriver,
				getRootElement().findElementByClassName(EditorConst.CLASS_BOTTOM_PANE_WRAPPER)
		);
	}

	@Step("Поиск контекс меню на странице редактора")
	public static ContextMenu findContextMenuOnPage(WebDriver webDriver) {
		var contextMenuWebElement = webDriver.getRootElement()
				.findElementByClassName(EditorConst.CLASS_ACTION_CONTEXT_MENU);
		Assertions.assertTrue(contextMenuWebElement.isFound(), "Элемент контекст меню должен быть найден");

		return new ContextMenu(webDriver, contextMenuWebElement);
	}

	public Modal findModalOnPage() {
		return findModalOnPage(webDriver);
	}

	@Step("Поиск модального окна на странице редактора")
	public static Modal findModalOnPage(WebDriver webDriver) {
		var modalElement = webDriver.getRootElement()
				.findElementByClassName(EditorConst.CLASS_MODAL_WRAPPER);

		Assertions.assertTrue(modalElement.isFound(), "Элемент модального окна должно быть найдено");
		return new Modal(webDriver, modalElement);
	}
}
