package context;

import page_object_manager.PageObjectManager;

public class ScenarioContext {

	private PageObjectManager pageObjectManager;

	public ScenarioContext() {

		pageObjectManager = new PageObjectManager();
	}

	public PageObjectManager getPageObjectManager() {

		return pageObjectManager;
	}
}