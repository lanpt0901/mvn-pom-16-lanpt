package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import pageUI.bankGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickHereLink() {
		waitForElementVisible(driver, RegisterPageUI.HERE);
		clickToElement(driver, RegisterPageUI.HERE);
		
	}

	public void fillEmailToRegister(String string) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL);
		sendKeyToElement(driver, RegisterPageUI.EMAIL, string);
	}

	public void clickSubmitBtn() {
		waitForElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
		
	}

	public String getUsername() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID);
		return getTextOfElement(driver, RegisterPageUI.USER_ID).trim();
	}

	public String getPassword() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD);
		return getTextOfElement(driver, RegisterPageUI.PASSWORD).trim();
	}


}
