package pageFactory;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

	//Web browser
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	

	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, WebElement element, String text) {
		element.clear();
		element.sendKeys(text);;
	}
	
	public String getTextOfElement(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	public void selectItemInDropdown(WebDriver driver, WebElement element, String selectedItem) {
		select = new Select(element);
		select.selectByVisibleText(selectedItem);
	}
	
	public String getSelectedItemInDropdown(WebDriver driver, WebElement element) {
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	
	public Boolean isDropdownMutiple(WebDriver driver, WebElement element) {
		select = new Select(element);
		return select.isMultiple();
	}
	

	public int countElementNumber(WebDriver driver, List<WebElement> element) {
		return element.size();
	}
	
	public void checkTheCheckBox(WebDriver driver, WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckTheCheckBox(WebDriver driver, WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public Boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
	
	public Boolean isElementSelected(WebDriver driver, WebElement element) {
		return element.isSelected();
	}
	
	public Boolean isElementEnabled(WebDriver driver, WebElement element) {
		return element.isEnabled();
	}
	
	//Wait
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	int longTimeout = 30;
	WebDriverWait explicitWait;
	Select select;
	JavascriptExecutor jsExecutor;
	Actions action;
}
