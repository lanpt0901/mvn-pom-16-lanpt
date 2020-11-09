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
import pageObject.bankGuru.DeleteCustomerPageObject;
import pageObject.bankGuru.EditCustomerPageObject;
import pageObject.bankGuru.HomePageObject;
import pageObject.bankGuru.LoginPageObject;
import pageObject.bankGuru.NewAccountPageObject;
import pageObject.bankGuru.NewCustomerPageObject;
import pageObject.bankGuru.PageGenerator;
import pageObject.bankGuru.RegisterPageObject;

public class Login_01_RegisterOrLoginPage_LifeCycle {
	WebDriver driver;
	String username;
	String password;
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
		/*driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");*/
		driver = driverManager.getDriver(GlobalConstants.BANKGURU_URL);
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	@Test
	public void TC_01_CreateNewAccount() {
		registerPage = new RegisterPageObject(driver);
		loginUrl = registerPage.getCurrentUrl(driver);
		registerPage.clickHereLink();
		registerPage.fillEmailToRegister("lanpt2020@gmail.com");
		registerPage.clickSubmitBtn();
		username = registerPage.getUsername();
		password = registerPage.getPassword();
		registerPage.openUrl(driver, loginUrl);
		loginPage = PageGenerator.getLoginPageObject(driver);
	}

	// mngr265418
	// ErUdYgy
	@Test
	public void TC_02_Login() {
		loginPage.fillUsername(username);
		loginPage.fillPassword(password);
		loginPage.clickSubmitBtn();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHelloTextDisplay(username));
	}
	
	@Test
	public void TC_03_TestMenu() {
		newCustomerPage = homePage.clickNewCustomerMenu(driver);
		editCustomerPage = newCustomerPage.clickEditCustomerMenu(driver);
		deleteCustomerPage = editCustomerPage.clickDeleteCustomerMenu(driver);
		newCustomerPage = deleteCustomerPage.clickNewCustomerMenu(driver);
		newAccountPage = newCustomerPage.clickNewAccountMenu(driver);
	}
	
}
