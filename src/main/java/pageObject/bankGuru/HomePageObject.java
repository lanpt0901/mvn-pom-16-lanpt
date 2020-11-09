package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import pageUI.bankGuru.HomePageUI;

public class HomePageObject extends AbstractPage{

	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHelloTextDisplay(String username) {
		return  isElementDisplayed(driver, "//td[text()='" + HomePageUI.MANGER_ID + username + "']");
	}


}
