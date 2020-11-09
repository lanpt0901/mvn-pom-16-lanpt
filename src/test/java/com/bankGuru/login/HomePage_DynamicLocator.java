package com.bankGuru.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.GlobalConstants;
import pageObject.bankGuru.AbstractPage;
import pageObject.bankGuru.DeleteCustomerPageObject;
import pageObject.bankGuru.EditCustomerPageObject;
import pageObject.bankGuru.HomePageObject;
import pageObject.bankGuru.LoginPageObject;
import pageObject.bankGuru.NewAccountPageObject;
import pageObject.bankGuru.NewCustomerPageObject;
import pageObject.bankGuru.PageGenerator;
import pageObject.bankGuru.RegisterPageObject;

public class HomePage_DynamicLocator extends AbstractPage {
	WebDriver driver;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	HomePageObject homePage;
	String loginUrl;
	DriverManager driverManager;

	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	private NewAccountPageObject newAccountPage;

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(GlobalConstants.BANKGURU_URL);

		loginPage = PageGenerator.getLoginPageObject(driver);
		loginPage.fillUsername(GlobalConstants.BANK_GURU_USERNAME);
		loginPage.fillPassword(GlobalConstants.BANKGURU_PASSWORD);
		loginPage.clickSubmitBtn();
		homePage = PageGenerator.getHomePage(driver);
		Assert.assertTrue(homePage.isHelloTextDisplay(GlobalConstants.BANK_GURU_USERNAME));
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	@Test
	public void TC_04_TestDynamicMenu() {
		newCustomerPage = (NewCustomerPageObject) homePage.clickDynamicMenu(driver, "New Customer");
		editCustomerPage = (EditCustomerPageObject) newCustomerPage.clickDynamicMenu(driver, "Edit Customer");
		deleteCustomerPage = (DeleteCustomerPageObject) editCustomerPage.clickDynamicMenu(driver, "Delete Customer");
		newCustomerPage = (NewCustomerPageObject) deleteCustomerPage.clickDynamicMenu(driver, "New Customer");
		newAccountPage = (NewAccountPageObject) newCustomerPage.clickDynamicMenu(driver, "New Account");
		
	}
}
