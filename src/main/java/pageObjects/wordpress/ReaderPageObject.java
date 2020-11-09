package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import pageUI.wordpress.AbstractPageUI;
import pageUI.wordpress.ReaderPageUI;

public class ReaderPageObject extends AbstractPage{
	
	WebDriver driver;

	public ReaderPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public MySitePageObject goToMySitePage() {
		waitForElementVisible(driver, AbstractPageUI.MY_SITE_SPAN);
		clickToElement(driver, AbstractPageUI.MY_SITE_SPAN);
		return PageGenerator.getMySitePage(driver);
	}
	
	public String getHeaderSuccess() {
		waitForElementVisible(driver, ReaderPageUI.HEADER_READER_PAGE);
		return getTextOfElement(driver, ReaderPageUI.HEADER_READER_PAGE).trim();
	}
	
	public String getDashboardHeaderSuccess() {
		waitForElementVisible(driver, ReaderPageUI.HEADER_DASHBOARD_PAGE);
		return getTextOfElement(driver, ReaderPageUI.HEADER_DASHBOARD_PAGE).trim();
	}
}
