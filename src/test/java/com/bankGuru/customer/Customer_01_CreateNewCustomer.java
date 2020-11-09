package com.bankGuru.customer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pageObject.bankGuru.HomePageObject;
import pageObject.bankGuru.LoginPageObject;
import pageObject.bankGuru.RegisterPageObject;

public class Customer_01_CreateNewCustomer {
	WebDriver driver;
	String username;
	String password;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	HomePageObject homePage;
	String loginUrl;

  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://demo.guru99.com/v4/");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  
  
}
