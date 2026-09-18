package context;

import java.util.List;

import page_object_manager.PageObjectManager;

public class ScenarioContext_2 {

	private PageObjectManager pageObjectManager;
	
	private List<String> cartProductNames;

	public ScenarioContext_2() {

		pageObjectManager = new PageObjectManager();
	}

	public PageObjectManager getPageObjectManager() {

		return pageObjectManager;
	}
	
	
}