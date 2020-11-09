package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.testdata.EndUser;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import pageObject.nopcommerce.HomePageObject;
import pageObject.nopcommerce.PageGenerator;
import pageObject.nopcommerce.RegisterPageObject;

public class Register_01_DataTest_In_External_File extends AbstractTest{
	
	private WebDriver driver;
	private DriverManager driverManager;
	private String firstName = EndUser.Register.FIRST_NAME;
	private String lastName = EndUser.Register.LAST_NAME;
	private String email = EndUser.Register.EMAIL;
	private String password = EndUser.Register.PASSWORD;
	private String company = EndUser.Register.COMPANY;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(url);

		homePage = PageGenerator.getHomePage(driver);
		homePage.moveToRegisterPage();
	}

	@Test
	public void TC_01_Register_Success() {
		registerPage = PageGenerator.getRegisterPage(driver);
		registerPage.chooseMaleGender();
		registerPage.inputToFirstNameBox(firstName);
		registerPage.inputToLastNameBox(lastName);
		registerPage.inputToCompanyBox(company);
		//registerPage.chooseDayOfBirthday();
		registerPage.inputToPassword(password);
		registerPage.inputToConfirmPassword(password);
		registerPage.inputToEmailBox(email);
		System.out.printf("First Name: %s\nLast Name: %s \\nPassword: %s\\nCompany: %s",firstName, lastName, password, company);
		registerPage.submit();
		
	}
	
	@AfterClass
	public void afterClass() {
//		closeBrowserAndDriver(driver);
	}

}
