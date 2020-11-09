package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

public class PageGenerator {

	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static ReaderPageObject getDashboarPage(WebDriver driver) {
		return new ReaderPageObject(driver) ;
	}
	
	public static MediaPageObject getMediaPage(WebDriver driver) {
		return new MediaPageObject(driver) ;
	}
	
	public static MySitePageObject getMySitePage(WebDriver driver) {
		return new MySitePageObject(driver) ;
	}
	
}
