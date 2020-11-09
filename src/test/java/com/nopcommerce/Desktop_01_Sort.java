package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.AbstractTest;
import pageObject.nopcommerce.DesktopPageObject;

public class Desktop_01_Sort extends AbstractTest{

	private WebDriver driver;
	private DriverManager driverManager;
	private DesktopPageObject desktopPage;

	@BeforeClass
	@Parameters({"browser","url"})
	public void beforeClass(String browserName, String url) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(url);
		desktopPage = new DesktopPageObject(driver);
	}

	@Test
	public void TC_01_Sort_Name() {
		desktopPage.selectDropdownBox("Name: A to Z");
//		desktopPage.sleepInSecond(5);
		Assert.assertTrue(desktopPage.isNameSortedAscending());
		desktopPage.selectDropdownBox("Name: Z to A");
//		desktopPage.sleepInSecond(5);
		Assert.assertTrue(desktopPage.isNameSortedDescending());
		
	}

	@Test
	public void TC_02_Sort_Price() {
		desktopPage.selectDropdownBox("Price: Low to High");
		Assert.assertTrue(desktopPage.isPriceSortedAscending());
		desktopPage.selectDropdownBox("Price: High to Low");
		Assert.assertTrue(desktopPage.isPriceSortedDescending());
	} 
	
	@Test
	public void TC_03_Sort_Position() {
		
		
	}
	
	@Test
	public void TC_04_Sort_CreatedOn() {
		
	}
	

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
