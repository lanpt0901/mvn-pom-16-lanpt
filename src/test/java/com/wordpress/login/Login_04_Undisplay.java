package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.GlobalConstants;
import pageObjects.wordpress.LoginPageObject;
import pageObjects.wordpress.MySitePageObject;
import pageObjects.wordpress.ReaderPageObject;

public class Login_04_Undisplay {

	private WebDriver driver;
	private DriverManager driverManager;
	private MySitePageObject mySitePage;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browserName) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(GlobalConstants.WORPRESS_URL);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public void TC_01_LoginSuccessAndGoToDashboard() {
		LoginPageObject loginPage = new LoginPageObject(driver);
		loginPage.sendEmailOrUsername("lanpt2011");
		loginPage.clickToContinueButton();
		loginPage.sendPassword("Noidinao890");
		loginPage.clickToContinueButton();

		ReaderPageObject dashboardPage = new ReaderPageObject(driver);
		Assert.assertEquals(dashboardPage.getDashboardHeaderSuccess(), "Let's get your site a domain!");
		
		mySitePage = dashboardPage.goToMySitePage();
	}
	
	@Test
	public void TC_01_LoginSuccessAndGoToDashboardFC() {
		LoginPageObject loginPage = new LoginPageObject(driver);
		loginPage.sendEmailOrUsername("automationeditor");
		loginPage.clickToContinueButton();
		loginPage.sendPassword("automationfc");
		loginPage.clickToContinueButton();

		ReaderPageObject readerPage = new ReaderPageObject(driver);
		mySitePage = readerPage.goToMySitePage();
		mySitePage.sleepInSecond(2);
		Assert.assertEquals(mySitePage.getHeaderSuccess(), "Stats and Insights");
	}
	
	@Test
	public void TC_02_Element_Displayed_In_DOM() {
		mySitePage.sleepInSecond(2);
		System.out.println("Start check sub menu displayed in dom Start: " + mySitePage.getDateNow());
		Assert.assertTrue(mySitePage.isSubmenuDisplayed("Stats"));
		System.out.println("Start check sub menu displayed in dom Start: " + mySitePage.getDateNow());
	}
	
	@Test
	public void TC_03_Element_Undisplayed_Not_In_DOM() {
		System.out.println("Start check sub menu undisplayed in dom Start: " + mySitePage.getDateNow());
		Assert.assertTrue(mySitePage.isMediaSubMenuUnDisplayed());
		System.out.println("Start check sub menu undisplayed in dom End: " + mySitePage.getDateNow());
		
		System.out.println("Start check menu undisplayed not in dom Start: " + mySitePage.getDateNow());
		Assert.assertTrue(mySitePage.isSettingsMenuUnDisplayed());
		System.out.println("Start check menu undisplayed not in dom End: " + mySitePage.getDateNow());
		
		
	}
}
