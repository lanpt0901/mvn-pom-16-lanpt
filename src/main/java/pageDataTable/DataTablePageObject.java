package pageDataTable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import dataTable.DataTableUI;

public class DataTablePageObject extends AbstractPage{
	private WebDriver driver;

	public DataTablePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sendKeyToTextBoxByColumnName(String columnName, String text) {
		waitForElementVisible(driver, DataTableUI.DYNAMIC_TEXTBOX_BY_COLUNM_NAME, columnName);
		sendKeyToElement(driver, text, DataTableUI.DYNAMIC_TEXTBOX_BY_COLUNM_NAME, columnName);
		sendKeyboardToElement(driver, DataTableUI.DYNAMIC_TEXTBOX_BY_COLUNM_NAME, Keys.ENTER, columnName);
	}

	public boolean isOnlyOneRowDisplay(String text) {
		waitForElementVisible(driver, DataTableUI.DYNAMIC_TD_DATA_ROW, text);
		int totalRowNumber = countElementNumber(driver, DataTableUI.DYNAMIC_TD_DATA_ROW, text);
		Boolean isDisplay = isElementDisplayed(driver, DataTableUI.DYNAMIC_TD_DATA_ROW, text);
		return totalRowNumber == 1 && isDisplay;
	}

	public void paginationByPageNumber(String pageNumber) {
		waitForElementVisible(driver, DataTableUI.DYNAMIC_PAGE, pageNumber);
		clickToElement(driver, DataTableUI.DYNAMIC_PAGE, pageNumber);
	}

	public boolean isSelectedPage(String pageNumber) {
		waitForElementVisible(driver, DataTableUI.DYNAMIC_PAGING_ACTIVE, pageNumber);
		return isElementDisplayed(driver, DataTableUI.DYNAMIC_PAGING_ACTIVE, pageNumber);
	}

	public void clickEditOrRemoveButtonByColumnNameAndText(String columnName, String text, String button) {
		waitForElementVisible(driver, DataTableUI.DYNAMIC_EDIT_OR_REMOVE_BY_COLUNM_NAME_AND_TEXT, columnName, text, button);
		clickToElement(driver, DataTableUI.DYNAMIC_EDIT_OR_REMOVE_BY_COLUNM_NAME_AND_TEXT, columnName, text, button);
	}

	public void sendKeyToTextBoxByColumnNameAndRowIndex(String columnName, String rowIndex, String inputText) {
		waitForElementVisible(driver, DataTableUI.NUMBER_OF_ROW_BEFORE_CELL_WITH_COLUMN_NAME, columnName);
		int columnIndex = countElementNumber(driver, DataTableUI.NUMBER_OF_ROW_BEFORE_CELL_WITH_COLUMN_NAME, columnName) + 1;
		System.out.println("columnIndex = " + columnIndex);
		
		sendKeyToElement(driver, inputText, DataTableUI.DYNAMIC_CELL_BY_ROW_INDEX_COLUMN_INDEX,  rowIndex, String.valueOf(columnIndex));
		
	}
	
	
}
