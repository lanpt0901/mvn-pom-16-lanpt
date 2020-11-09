package pageObject.nopcommerce;


import org.openqa.selenium.WebDriver;

import pageUI.nopcommerce.DesktopPageUI;

public class DesktopPageObject extends AbstractPage{

	WebDriver driver;
	public DesktopPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void selectDropdownBox(String selectedItem) {
		waitForElementVisible(driver, DesktopPageUI.SORT_DROPDOWN);
		selectItemInDropdown(driver, DesktopPageUI.SORT_DROPDOWN, selectedItem);
		System.out.println("======================"+driver.toString());
		if(driver.toString().toLowerCase().contains("internet explorer")) {
			sleepInSecond(5);
			System.out.println("vao day");
		}
	}
	public boolean isNameSortedAscending() {
		waitForElementsVisible(driver, DesktopPageUI.PRODUCT_NAME);
		return isTextSortedAscending(driver, DesktopPageUI.PRODUCT_NAME);
	}
	public boolean isNameSortedDescending() {
		waitForElementsVisible(driver, DesktopPageUI.PRODUCT_NAME);
		return isTextSortedDescending(driver, DesktopPageUI.PRODUCT_NAME);
	}

	public boolean isPriceSortedAscending() {
		waitForElementsVisible(driver, DesktopPageUI.PRODUCT_PRICE);
		return isPriceSortedAscending(driver, DesktopPageUI.PRODUCT_PRICE);
	}
	public boolean isPriceSortedDescending() {
		waitForElementsVisible(driver, DesktopPageUI.PRODUCT_PRICE);
		return isPriceSortedDescending(driver, DesktopPageUI.PRODUCT_PRICE);
	}
}
