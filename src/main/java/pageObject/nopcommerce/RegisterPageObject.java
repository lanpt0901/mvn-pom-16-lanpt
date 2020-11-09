package pageObject.nopcommerce;


import org.openqa.selenium.WebDriver;

import pageUI.nopcommerce.HomePageUI;
import pageUI.nopcommerce.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{

	WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void chooseMaleGender() {
		waitForElementsVisible(driver, RegisterPageUI.MALE);
		clickToElement(driver, RegisterPageUI.MALE);
	}
	public void inputToFirstNameBox(String firstName) {
		waitForElementsVisible(driver, RegisterPageUI.FIRST_NAME);
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME, firstName);
	}
	public void inputToLastNameBox(String lastName) {
		waitForElementsVisible(driver, RegisterPageUI.LAST_NAME);
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME, lastName);
	}
	public void inputToPassword(String password) {
		waitForElementsVisible(driver, RegisterPageUI.PASSWORD);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD, password);
	}
	public void inputToConfirmPassword(String password) {
		waitForElementsVisible(driver, RegisterPageUI.CONFIRM_PASSWORD);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD, password);
	}
	public void submit() {
		waitForElementsVisible(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		
	}
	public void inputToCompanyBox(String company) {
		waitForElementsVisible(driver, RegisterPageUI.COMPANY);
		sendKeyToElement(driver, RegisterPageUI.COMPANY, company);
	}

	public void inputToEmailBox(String email) {
		waitForElementsVisible(driver, RegisterPageUI.EMAIL);
		sendKeyToElement(driver, RegisterPageUI.EMAIL, email);
	}
}
