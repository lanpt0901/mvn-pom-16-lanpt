package com.bankGuru.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObject.bankGuru.RegisterPageObject;

public class Common_01_RegisterUser extends AbstractTest {

	private DriverManager driverManager;
	private WebDriver driver;
	RegisterPageObject registerPage;
	public static String username;
	public static String password;
	
	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browserName) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(GlobalConstants.BANKGURU_URL);
		log.info("Common Register - beforeTest: create common user");
		registerPage = new RegisterPageObject(driver);
		registerPage.clickHereLink();
		registerPage.fillEmailToRegister("lanpt2020@gmail.com");
		registerPage.clickSubmitBtn();
		username = registerPage.getUsername();
		password = registerPage.getPassword();
	}
	
	@AfterTest(alwaysRun=true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
		
	}
}
