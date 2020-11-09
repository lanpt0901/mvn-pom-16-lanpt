package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.testdata.EndUser;
import com.nopcommerce.testdata.EndUserJson;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import pageObject.nopcommerce.HomePageObject;
import pageObject.nopcommerce.PageGenerator;
import pageObject.nopcommerce.RegisterPageObject;

public class Register_01_DataTest_In_External_Json_File extends AbstractTest{
	
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

		System.out.println(GlobalConstants.USER_DIR + "\\src\\test\\java\\com\\nopcommerce\\testdata\\EndUser.json");
		EndUserJson data = EndUserJson.getEndUserJson(GlobalConstants.USER_DIR + "\\src\\test\\java\\com\\nopcommerce\\testdata\\EndUser.json");
		System.out.println("======================" + data.getFirstName());
		firstName = data.getFirstName();
		lastName = data.getLastName();
		company = data.getCompany();
		password = data.getPassword();
		email = data.getEmail();
		
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
