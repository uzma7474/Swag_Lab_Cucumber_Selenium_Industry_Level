
package stepdefinitions;

import actions.MenuAction;
import assertions.MenuAssertions;
import components.MenuComponent;
import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page_object_manager.PageObjectManager;

public class MenuSteps {

	private static final Logger log = LoggerFactory.getLogger(MenuSteps.class);

	private final MenuAction menuAction;
	private final MenuAssertions menuAssertions;
	
	private final MenuComponent menu;

	public MenuSteps(ScenarioContext scenarioContext) {

		PageObjectManager pageObjectManager = scenarioContext.getPageObjectManager();
		
		this.menu = pageObjectManager.getMenuComponent();

		this.menuAction = new MenuAction(menu, pageObjectManager);

		this.menuAssertions = new MenuAssertions(menu, pageObjectManager.getLoginPage(), pageObjectManager, scenarioContext);

		log.info("MenuSteps initialized successfully");
	}

	@When("the user opens the application menu")
	public void the_user_opens_the_application_menu() {

		log.info("User opens the application menu");

		menuAction.openApplicationMenu();
	}

	@When("the user logs out")
	public void the_user_logs_out() {

		log.info("User logs out from the application");

		menuAction.logout();
	}

	@Then("the user should be redirected to the Login page")
	public void the_user_should_be_redirected_to_the_login_page() {

		log.info("Verifying user is redirected to Login page");

		menuAssertions.verifyRedirectedToLoginPage();
	}
	
	@When("the user clicks the Menu button")
	public void the_user_clicks_the_menu_button() {

	    log.info("User clicks Menu button");

	    menuAction.clickMenuButton();
	}

	@Then("the sidebar menu should be displayed")
	public void the_sidebar_menu_should_be_displayed() {

	    log.info("Verifying sidebar menu is displayed");

	    menuAssertions.verifyMenuDisplayed();
	}

	@When("the user clicks the All Items option")
	public void the_user_clicks_the_all_items_option() {

	    log.info("User clicks All Items");

	    menuAction.clickAllItems();
	}

	@When("the user clicks the Dynamic Catalog option")
	public void the_user_clicks_the_dynamic_catalog_option() {

	    log.info("User clicks Dynamic Catalog");

	    menuAction.clickDynamicCatalog();
	}

	@When("the user clicks the Lazy Load option")
	public void the_user_clicks_the_lazy_load_option() {

	    log.info("User clicks Lazy Load");

	    menuAction.clickLazyLoad();
	}

	@When("the user clicks the Spinner option")
	public void the_user_clicks_the_spinner_option() {

	    log.info("User clicks Spinner");

	    menuAction.clickSpinner();
	}

	@When("the user clicks the Slider option")
	public void the_user_clicks_the_slider_option() {

	    log.info("User clicks Slider");

	    menuAction.clickSlider();
	}

	@When("the user clicks the Logout option")
	public void the_user_clicks_the_logout_option() {

	    log.info("User clicks Logout");

	    menuAction.clickLogout();
	}

	@When("the user clicks the Close Menu button")
	public void the_user_clicks_the_close_menu_button() {

	    log.info("User clicks Close Menu");

	    menuAction.closeMenu();
	}
	
	@Then("the All Items option should be displayed")
	public void the_all_items_option_should_be_displayed() {

	    log.info("Verifying All Items option is displayed");

	    menuAssertions.verifyInventorySidebarLinkDisplayed();

	    log.info("All Items option is displayed successfully");
	}


	@Then("the Dynamic Catalog option should be displayed")
	public void the_dynamic_catalog_option_should_be_displayed() {

	    log.info("Verifying Dynamic Catalog option is displayed");

	    menuAssertions.verifyDynamicCatalogDisplayed();

	    log.info("Dynamic Catalog option is displayed successfully");
	}


	@Then("the About option should be displayed")
	public void the_about_option_should_be_displayed() {

	    log.info("Verifying About option is displayed");

	    menuAssertions.verifyAboutDisplayed();

	    log.info("About option is displayed successfully");
	}


	@Then("the Logout option should be displayed")
	public void the_logout_option_should_be_displayed() {

	    log.info("Verifying Logout option is displayed");

	    menuAssertions.verifyLogoutDisplayed();

	    log.info("Logout option is displayed successfully");
	}


	@Then("the Reset App State option should be displayed")
	public void the_reset_app_state_option_should_be_displayed() {

	    log.info("Verifying Reset App State option is displayed");

	    menuAssertions.verifyResetAppStateDisplayed();

	    log.info("Reset App State option is displayed successfully");
	}
	
	@Then("the Close Menu button should be displayed")
	public void the_close_menu_button_should_be_displayed() {

	    log.info("Verifying Close Menu button is displayed");

	    menuAssertions.verifyCloseMenuDisplayed();

	    log.info("Close Menu button is displayed successfully");
	}
	
	@Then("the Dynamic Catalog submenu should be displayed")
	public void the_dynamic_catalog_submenu_should_be_displayed() {

	    log.info("Verifying Dynamic Catalog submenu is displayed");

	    menuAssertions.verifyDynamicCatalogSubmenuDisplayed();

	    log.info("Dynamic Catalog submenu is displayed successfully");
	}
	
	
	
}
