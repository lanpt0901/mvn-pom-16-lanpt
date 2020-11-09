package pageObject.nopcommerce;


import org.openqa.selenium.WebDriver;

import pageUI.nopcommerce.HomePageUI;


public class HomePageObject extends AbstractPage{

	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void moveToRegisterPage() {
		waitForElementVisible(driver, HomePageUI.REGISTER);
		clickToElement(driver, HomePageUI.REGISTER);
	}
}
