package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //this class nayf
	}

	@FindBy(how = How.ID, using="usernameOrEmail") 
	private WebElement usernameTextBox;

	@FindBy(how = How.ID, using="password") 
	private WebElement passwordTextBox;

	@FindBy(how = How.XPATH, using="//div[@class='login__form-action']/button") 
	private WebElement continueButton;

	@FindBy(how = How.XPATH, using="//div[contains(@class,'form-input-validation')]/span") 
	private WebElement errorMessage;
	
	
	public void sendEmailOrUsername(String text) {
		waitForElementVisible(driver, usernameTextBox);
		sendKeyToElement(driver, usernameTextBox, text);
	}

	public void clickToContinueButton() {
		clickToElement(driver, continueButton);
	}

	public String getErrorMsg() {
		waitForElementVisible(driver, errorMessage);
		return getTextOfElement(driver, errorMessage).trim();
	}

	public void sendPassword(String string) {
		waitForElementVisible(driver, passwordTextBox);
		sendKeyToElement(driver, passwordTextBox, string);
	}

}
