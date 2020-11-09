package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import pageUI.bankGuru.LoginPageUI;

public class LoginPageObject extends AbstractPage{

	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void fillUsername(String username) {
		waitForElementVisible(driver, LoginPageUI.UID);
		sendKeyToElement(driver, LoginPageUI.UID, username);
	}

	public void fillPassword(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD);
		sendKeyToElement(driver, LoginPageUI.PASSWORD, password);
	}

	public void clickSubmitBtn() {
		waitForElementVisible(driver, LoginPageUI.SUBMIT_BUTTON);
		clickToElement(driver, LoginPageUI.SUBMIT_BUTTON);
	}

}
