package com.nopcommerce;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import commons.DataHelper;
import pageObject.nopcommerce.HomePageObject;
import pageObject.nopcommerce.PageGenerator;
import pageObject.nopcommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Register_01_DataTest_In_Class extends AbstractTest{
	
	private WebDriver driver;
	private DriverManager driverManager;
	private DataHelper data;
	private String firstName;
	private String lastName;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String city;
	private String email;
	private String password;
	private String company;
	private String male="M";
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(url);
		data = DataHelper.getData();
		firstName = data.getFirstName();
		lastName = data.getLastName();
		fullName = data.getFullName();
		address = data.getAddress();
		company = data.getCompanyName();
		city = data.getCityName();
		password = data.getPassword();
		phoneNumber = data.getPhone();
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
		closeBrowserAndDriver(driver);
	}

}
