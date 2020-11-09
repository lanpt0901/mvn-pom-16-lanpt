package com.wordpress.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageFactory.DashboardPageObject;
import pageFactory.LoginPageObject;
import pageFactory.PageGenerator;

public class Login_02_ValidateLoginForm_PageFactory {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @BeforeMethod
  public void beforeMethod() {
	  driver.get("https://wordpress.com/log-in?redirect_to=https%3A%2F%2Fwordpress.com%2Fstart%2Fdomains");
  }
  
  @AfterClass
  public void afterClass() {
	 // driver.quit();
  }

  @Test
  public void TC_01_fillEmptyEmail() {
	  loginPage = new LoginPageObject(driver);
	  loginPage.sendEmailOrUsername("");
	  loginPage.clickToContinueButton();
	  Assert.assertEquals(loginPage.getErrorMsg(), "Please enter a username or email address.");
  }
  
  @Test
  public void TC_02_fillWrongFormatEmail() {
	  loginPage = new LoginPageObject(driver);
	  loginPage.sendEmailOrUsername("123@123.com");
	  loginPage.clickToContinueButton();
	  Assert.assertEquals(loginPage.getErrorMsg(), "Please log in using your WordPress.com username instead of your email address.");
  }
  
  @Test
  public void TC_03_fillRightEmailAndInvalidPass() {
	  loginPage = new LoginPageObject(driver);
	  loginPage.sendEmailOrUsername("abc123@gmail.com");
	  loginPage.clickToContinueButton();
	  loginPage.sendPassword("");
	  loginPage.clickToContinueButton();
	  Assert.assertEquals(loginPage.getErrorMsg(), "Don't forget to enter your password.");
  }
  
  @Test
  public void TC_04_fillRightEmailAndWrongPass() {
	  loginPage = new LoginPageObject(driver);
	  loginPage.sendEmailOrUsername("abc123@gmail.com");
	  loginPage.clickToContinueButton();
	  loginPage.sendPassword("123");
	  loginPage.clickToContinueButton();
	  Assert.assertEquals(loginPage.getErrorMsg(), "Oops, that's not the right password. Please try again!");
  }
  
  @Test
  public void TC_05_LoginSuccessAndGoToDashboard() {
	  loginPage = new LoginPageObject(driver);
	  loginPage.sendEmailOrUsername("automationeditor");
	  loginPage.clickToContinueButton();
	  loginPage.sendPassword("automationfc");
	  loginPage.clickToContinueButton();
	  
	  dashboardPage = PageGenerator.getDashboardPageObject(driver);
	  Assert.assertEquals(dashboardPage.getHeaderSuccessPage(), "Let's get your site a domain!");
  }
}
