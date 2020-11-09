package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import pageUI.wordpress.AbstractPageUI;
import pageUI.wordpress.MySitePageUI;

public class MySitePageObject extends AbstractPage{
	
	WebDriver driver;

	public MySitePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getHeaderSuccess() {
		waitForElementVisible(driver, MySitePageUI.HEADER_MY_SITE);
		return getTextOfElement(driver, MySitePageUI.HEADER_MY_SITE).trim();
	}

	public boolean isSubmenuDisplayed(String... locator) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU, locator);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_MENU, locator);
	}
	
	public boolean isSubmenuUnDisplayed(String locator, String... values) {
		return isElementUnDisplayed(driver, AbstractPageUI.DYNAMIC_MENU, values);
	}

	public boolean isMediaSubMenuUnDisplayed() {
		return isElementUnDisplayed(driver, AbstractPageUI.MEDIA_SUB_MENU);
	}

	public boolean isSettingsMenuUnDisplayed() {
		return isElementUnDisplayed(driver, AbstractPageUI.SETTINGS_MENU);
	}
	
}
