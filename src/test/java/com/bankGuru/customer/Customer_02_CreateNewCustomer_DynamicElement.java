package com.bankGuru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankGuru.common.Common_01_RegisterUser;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObject.bankGuru.CustomerRegistedMessagePageObject;
import pageObject.bankGuru.CustomerUpdateMsgPageObject;
import pageObject.bankGuru.EditCustomerPageObject;
import pageObject.bankGuru.FirstEditCustomerPageObject;
import pageObject.bankGuru.HomePageObject;
import pageObject.bankGuru.LoginPageObject;
import pageObject.bankGuru.NewCustomerPageObject;
import pageObject.bankGuru.PageGenerator;

public class Customer_02_CreateNewCustomer_DynamicElement extends AbstractTest {

	private DriverManager driverManager;
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private FirstEditCustomerPageObject firstEditCustomerPage;
	private CustomerRegistedMessagePageObject customerRegMsgPage;
	private String newCustomerId;
	private CustomerUpdateMsgPageObject customerUpdateMsgPage;
	
	//create data test
	String email="jeniferpham@gmail.com";
	String cusName = "Jenifer Pham";
	String cusGender = "female";
	String cusBirthday ="1987-08-01";
	String cusAddress = "114 Yen Hoa\nTrung Hoa";
	String cusAddressInLine = cusAddress.replace("\n", " ");
	String cusCity = "Ha Noi";
	String cusState = "Viet Nam";
	String cusPin = "321156";
	String cusMobileNumber = "0987999456";
	String cusEmail = "jun" + randomNumber() + "@gmail.com";
	String cusPass = "abc@123";
	String cusId;
	
	//edit data test
	String editAddress = "234 Yen Hoa\nTrung Hoa\nCau Giay\nHa Noi";
	String editAddressInLine = editAddress.replace("\n", " ");
	String editCity = "Ha Noi";
	String editState = "Viet Nam";
	String editPin = "123569";
	String editMobileNumber = "0987999988";
	String editEmail = "jun" + randomNumber() + "@gmail.com";

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browserName) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(GlobalConstants.BANKGURU_URL);

		loginPage = new LoginPageObject(driver);
		loginPage.sendKeyToDynamicInput(driver, "uid", Common_01_RegisterUser.username);
		loginPage.sendKeyToDynamicInput(driver, "password", Common_01_RegisterUser.password);
		loginPage.clickToDynamicButton(driver, "btnLogin");
		
		log.info("BeforeClass - login with username = "+ Common_01_RegisterUser.username);
		
		homePage = new HomePageObject(driver);
		verifyTrue(homePage.isHelloTextDisplay(Common_01_RegisterUser.username));
	}

	@Test
	public void TC_01_CreateNewCustomerSuccess() {
		log.info("Create new customer success - Step 01: click to New customer menu");
		homePage.clickToDynamicLink(driver, "New Customer");
		newCustomerPage = PageGenerator.getNewCustomerPage(driver);
		log.info("Create new customer success - Step 02: Open New customer page and fill data to create customer");
		newCustomerPage.sendKeyToDynamicInput(driver, "name", cusName);
		log.info("Create new customer success - Step 03: fill birthday");
		newCustomerPage.sendKeyToDynamicInput(driver, "dob", cusBirthday);
		log.info("Create new customer success - Step 04: choose gender");
		newCustomerPage.clickToRadioButton(driver, "f");
		newCustomerPage.sendKeyToDynamicArea(driver, "addr", cusAddressInLine);
		newCustomerPage.sendKeyToDynamicInput(driver, "city", cusCity);
		newCustomerPage.sendKeyToDynamicInput(driver, "state", cusState);
		newCustomerPage.sendKeyToDynamicInput(driver, "pinno", cusPin);
		newCustomerPage.sendKeyToDynamicInput(driver, "telephoneno", cusMobileNumber);
		newCustomerPage.sendKeyToDynamicInput(driver, "password", cusPass);
		newCustomerPage.sendKeyToDynamicInput(driver, "emailid", cusEmail);
		newCustomerPage.clickToDynamicButton(driver, "sub");
		
		customerRegMsgPage = PageGenerator.getCustomerRegMsgPageObject(driver);
		log.info("Create new customer success - Step 03: verify new customer by message");
		customerRegMsgPage.isDynamicPageOrMessageDisplayed(driver, "Customer Registered Successfully!!!");
		
		log.info("Create new customer success - Step 04: get new customer ID");
		newCustomerId = customerRegMsgPage.getDynamicTextInTable(driver, "Customer ID");
	}


	@Test
	public void TC_02_Verify_CreateNewCustomerSuccess() {
		log.info("Verify new customer success - Step 01: verify new customer by customer info in data table");
		customerRegMsgPage.sleepInSecond(3);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Customer Name"), cusName);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Birthdate"), cusBirthday);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Gender"), cusGender);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Address"), cusAddressInLine);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "City"), cusCity);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "State"), cusState);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Pin"), cusPin);
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Mobile No."), cusMobileNumber);
		log.info("Verify new customer success - Step 02: verify email " + cusEmail);
		log.info("Verify new customer success - Step 02: verify email in table " + customerRegMsgPage.getDynamicTextInTable(driver, "Email"));
		verifyEquals(customerRegMsgPage.getDynamicTextInTable(driver, "Email"), cusEmail);
		verifyTrue(customerRegMsgPage.isDynamicLinkDisplayed(driver, "Continue"));
		customerRegMsgPage.sleepInSecond(3);
		customerRegMsgPage.clickToDynamicLink(driver, "Continue");
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHelloTextDisplay(Common_01_RegisterUser.username));
		
	}
	
	@Test
	public void TC_03_EditCustomerSuccess() {
		homePage.clickToDynamicLink(driver, "Edit Customer");
		firstEditCustomerPage = PageGenerator.getFirstEditCustomerPage(driver);
		firstEditCustomerPage.sendKeyToDynamicInput(driver, "cusid", newCustomerId);
		firstEditCustomerPage.clickToDynamicButton(driver, "AccSubmit");
		editCustomerPage = PageGenerator.getEditCustomerPage(driver);
		verifyFalse(editCustomerPage.isInputEnable(driver, "name"));
		verifyFalse(editCustomerPage.isInputEnable(driver, "gender"));
		verifyFalse(editCustomerPage.isInputEnable(driver, "dob"));
		editCustomerPage.sendKeyToDynamicArea(driver, "addr", editAddress);
		editCustomerPage.sendKeyToDynamicInput(driver, "city", editCity);
		editCustomerPage.sendKeyToDynamicInput(driver, "state", editState);
		editCustomerPage.sendKeyToDynamicInput(driver, "pinno", editPin);
		editCustomerPage.sendKeyToDynamicInput(driver, "telephoneno", editMobileNumber);
		editCustomerPage.sendKeyToDynamicInput(driver, "emailid", editEmail);
		editCustomerPage.clickToDynamicButton(driver, "sub");
	}
	
	@Test
	public void TC_04_Verify_EditCustomerSuccess() {
		customerUpdateMsgPage = PageGenerator.getCustomerUpdateMsgPageObject(driver);
		//customerUpdateMsgPage.sleepInSecond(3);
		log.info("Verify edit customer success - Step 01: verify edit customer by customer info in data table");
		customerUpdateMsgPage.isDynamicPageOrMessageDisplayed(driver, "Customer details updated Successfully!!!");
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Customer Name"), cusName);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Birthdate"), cusBirthday);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Gender"), cusGender);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Address"), editAddressInLine);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "City"), editCity);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "State"), editState);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Pin"), editPin);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Mobile No."), editMobileNumber);
		verifyEquals(customerUpdateMsgPage.getDynamicTextInTable(driver, "Email"), editEmail);
		verifyTrue(customerUpdateMsgPage.isDynamicLinkDisplayed(driver, "Continue"));
		customerRegMsgPage.clickToDynamicLink(driver, "Continue");
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHelloTextDisplay(Common_01_RegisterUser.username));
		
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() {
//		closeBrowserAndDriver(driver);
		driver.quit();
	}
	
  
  
  
}
