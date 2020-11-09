package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.ReaderPageObject;
import pageObjects.wordpress.LoginPageObject;

public class PageGenerator {

	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static ReaderPageObject getDashboardPageObject(WebDriver driver) {
		return new ReaderPageObject(driver);
	}
}
