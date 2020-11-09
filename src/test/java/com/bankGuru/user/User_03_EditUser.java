package com.bankGuru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankGuru.common.Common_01_RegisterUser;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObject.bankGuru.HomePageObject;
import pageObject.bankGuru.LoginPageObject;

public class User_03_EditUser extends AbstractTest{

	private DriverManager driverManager;
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browserName) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(GlobalConstants.BANKGURU_URL);

		loginPage = new LoginPageObject(driver);
		loginPage.fillUsername(Common_01_RegisterUser.username);
		loginPage.fillPassword(Common_01_RegisterUser.password);
		loginPage.clickSubmitBtn();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHelloTextDisplay(Common_01_RegisterUser.username));
	}

	@Test
	public void TC_01_Edit_Name() {
		
	}

	@Test
	public void TC_02_Edit_Address() {
		
	}

	@Test
	public void TC_03_Edit_Phone() {
		
	}

	
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	

}
