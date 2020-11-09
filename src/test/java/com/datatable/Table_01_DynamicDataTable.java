package com.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BrowserFactory.DriverManager;
import BrowserFactory.DriverManagerFactory;
import commons.GlobalConstants;
import pageDataTable.DataTablePageObject;

public class Table_01_DynamicDataTable {

	private WebDriver driver;
	private DriverManager driverManager;
	private DataTablePageObject dataTablePage;
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browserName) {
		driverManager = DriverManagerFactory.getBrowser(browserName);
		driver = driverManager.getDriver(GlobalConstants.JQUERY_DATA_TABLE_URL);
		dataTablePage = new DataTablePageObject(driver);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void TC_01_Test_Input_TextBoxOfDataTable() {
		dataTablePage.sendKeyToTextBoxByColumnName("Country", "Afghanistan");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplay("Afghanistan"));
		dataTablePage.refresh(driver);
		dataTablePage.sendKeyToTextBoxByColumnName("Total", "791312");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplay("791312"));
		dataTablePage.refresh(driver);
		dataTablePage.sendKeyToTextBoxByColumnName("Females", "777");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplay("777"));
		
	}

	
	public void TC_02_changePage_DataTable() {
		dataTablePage.paginationByPageNumber("10");
		Assert.assertTrue(dataTablePage.isSelectedPage("10"));
		dataTablePage.paginationByPageNumber("3");
		Assert.assertTrue(dataTablePage.isSelectedPage("3"));
	}

	
	public void TC_03_editOrRemove_DataTable() {
		dataTablePage.clickEditOrRemoveButtonByColumnNameAndText("country", "Algeria", "remove");
		dataTablePage.clickEditOrRemoveButtonByColumnNameAndText("country", "Afghanistan", "remove");
		dataTablePage.sleepInSecond(2);
		dataTablePage.clickEditOrRemoveButtonByColumnNameAndText("country", "Albania", "edit");
	}
	
	@Test
	public void TC_04_DynamicRow() {
		dataTablePage.openUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		dataTablePage.sendKeyToTextBoxByColumnNameAndRowIndex("Contact Person", "1", "Pham Thi Lan");
		dataTablePage.sendKeyToTextBoxByColumnNameAndRowIndex("Company", "2", "VIVAS");
		
	}
}
