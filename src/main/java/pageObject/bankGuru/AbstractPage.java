package pageObject.bankGuru;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUI.bankGuru.AbstractPageUI;

public abstract class AbstractPage {

	//Web browser
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void sendKeyToAlert(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	//Window
	public void switchWindownByID(WebDriver driver, String parentID) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	public void switchWindownByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles(); //get all id of window
		for (String runWindow : allWindows) {
			if(driver.getTitle().equals(title)) {
				driver.switchTo().window(runWindow);
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}


	public Boolean areAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size() == 1) {
			return true;
		}
		return false;
	}
	
	public By byXpath(String locator) {
		return By.xpath(locator);
	}
	public By byXpath(String locator, String... values) {
		return By.xpath(castToObject(locator, values));
	}
	//Web element
	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
		return driver.findElement(byXpath(locator, values));
	}
	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}
	
	public String castToObject(String locator, String... values) {
		return locator = String.format(locator, (Object[]) values);
	}
	
	public void clickToElement(WebDriver driver, String locator, String... values) {
		findElementByXpath(driver, castToObject(locator, values)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String text) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public String getTextOfElement(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String selectedItem) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(selectedItem);
	}
	
	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public Boolean isDropdownMutiple(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.isMultiple();
	}
	
	/**
	 * 
	 * 1. Click to parent to expand all item</br>
	 * 2. Wait to all child items is displayed</br>
	 * 3. Add all child items in List</br>
	 * 4. Find the item = expected item to click (select expected item)</br>
	 * 5. Scroll to expected item to view (by javascript) because displayed item can be click</br>
	 * 6. Click to expected item</br> 
	 */
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		// 1 - Click vào thẻ (cha) để xổ ra tất cả các item con
		findElementByXpath(driver, parentLocator).click();
		sleepInSecond(1);
		// 2 - Chờ cho tất cả các item con được load ra
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));
		// Đưa tất cả các item trong dropdown vào 1 list để kiểm tra
		List<WebElement> allItems = findElementsByXpath(driver, childItemLocator);
		// 3 - Chạy qua tất cả các giá trị đang có trong list
		for (WebElement item : allItems) {
			// 4 - Kiểm tra xem text của các giá trị có item nào bằng vs text mong muốn ko
			if (item.getText().equals(expectedItem)) {
				// 5 - Scroll xuống đến đúng item này
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
  
	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}
	
	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}
	
	public void checkTheCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckTheCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public Boolean isElementDisplayed(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();
	}
	
	public Boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
	}
	
	public Boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}
	
	public Boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}
	
	/**
	 * Switch from default content to frame by WebElement (element is frame)
	 * for example: (https://kyna.vn/ has iframe FB fanpage: //div[@class='fanpage']//iframe )
	 * @param driver
	 * @param locator
	 */
	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}
	
	/**
	 * Switch from iFrame/frame to default Content page (parent page)
	 * @param driver
	 * @param locator
	 */
	public void switchToDefaultContent(WebDriver driver, String locator) {
		driver.switchTo().defaultContent(); 
	}
	
	//user interaction
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void moveToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}
	
	//javascript executor
	public void dragAndDrop(WebDriver driver, String targetLocator, String dragLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",findElementByXpath(driver, targetLocator));
		sleepInSecond(2);
		action.dragAndDrop(findElementByXpath(driver, dragLocator), driver.findElement(By.id("droptarget"))).perform();
		sleepInSecond(1);
	}
	
	public Object executeJavascriptToBrowser(WebDriver driver, String javascript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javascript);
	}
	
	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}
	
	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (Boolean) jsExecutor.executeScript("return arguments[0].complete "
								+ "&& typeof arguments[0].naturalWidth != 'undefined' "
								+ "&& arguments[0].naturalWidth > 0", findElementByXpath(driver, locator));
		return status;
	}
	
	//Wait
	public void waitForElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String...values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForAlertPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	//move to other page by click to common menu 

	public NewCustomerPageObject clickNewCustomerMenu(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.NEW_CUSTOMER_MENU);
		clickToElement(driver, AbstractPageUI.NEW_CUSTOMER_MENU);
		return PageGenerator.getNewCustomerPage(driver);
	}

	public EditCustomerPageObject clickEditCustomerMenu(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.EDIT_CUSTOMER_MENU);
		clickToElement(driver, AbstractPageUI.EDIT_CUSTOMER_MENU);
		return PageGenerator.getEditCustomerPage(driver);
	}  
	
	public DeleteCustomerPageObject clickDeleteCustomerMenu(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.DELETE_CUSTOMER_MENU);
		clickToElement(driver, AbstractPageUI.DELETE_CUSTOMER_MENU);
		return PageGenerator.getDeleteCustomerPage(driver);
	}
	
	public NewAccountPageObject clickNewAccountMenu(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.NEW_ACCOUNT_MENU);
		clickToElement(driver, AbstractPageUI.NEW_ACCOUNT_MENU);
		return PageGenerator.getNewAccountPage(driver);
	}
	
	public AbstractPage clickDynamicMenu(WebDriver driver, String menuNamePage) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU, menuNamePage);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU, menuNamePage);
		
		switch (menuNamePage) {
		case "New Customer":
			return PageGenerator.getNewCustomerPage(driver); 
		case "Edit Customer":
			return PageGenerator.getEditCustomerPage(driver); 
		case "Delete Customer":
			return PageGenerator.getDeleteCustomerPage(driver); 
		case "New Account":
			return PageGenerator.getNewAccountPage(driver); 
		default:
			return PageGenerator.getHomePage(driver); 
		}
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  
	// dynamic locator of dynamic page element
	public void sendKeyToDynamicInput(WebDriver driver, String elementName, String text) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_INPUT, elementName);
		if(elementName.equals("dob")) {
			removeAttributeInDOM(driver, AbstractPageUI.DYNAMIC_INPUT, "type", elementName);
		}
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_INPUT, text, elementName);
		
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... elementName) {
			jsExecutor = (JavascriptExecutor) driver;
			element = findElementByXpath(driver, locator, elementName);
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public void clickToDynamicButton(WebDriver driver, String buttonName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_INPUT, buttonName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_INPUT, buttonName);
	}

	public void sendKeyToDynamicArea(WebDriver driver, String elementName, String text) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_AREA, elementName);
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_AREA, text, elementName);
	}

	public void clickToRadioButton(WebDriver driver, String buttonValue) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, buttonValue);
		clickToElement(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, buttonValue);
	}

	public boolean isDynamicPageOrMessageDisplayed(WebDriver driver, String text) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_HEADING_PAGE, text);
		return findElementByXpath(driver, AbstractPageUI.DYNAMIC_HEADING_PAGE, text).isDisplayed();
	}
	
	public String getDynamicTextInTable(WebDriver driver, String rowName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TABLE_ROW_NAME, rowName);
		return getTextOfElement(driver, AbstractPageUI.DYNAMIC_TABLE_ROW_NAME, rowName);
	}
	
	public String getTextOfElement(WebDriver driver, String locator, String... rowName) {
		return findElementByXpath(driver, locator, rowName).getText();
		
	}
	public void clickToDynamicLink(WebDriver driver, String textLink) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LEFT_MENU, textLink);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LEFT_MENU, textLink);
	}
	public void sendKeyToElement(WebDriver driver, String locator, String text, String... locatorInput) {
		element = findElementByXpath(driver, locator, locatorInput);
		element.clear();
		element.sendKeys(text);
	}
	
	public boolean isInputEnable(WebDriver driver, String elementName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_INPUT, elementName);
		return findElementByXpath(driver, AbstractPageUI.DYNAMIC_INPUT, elementName).isEnabled();
	}
	
	public boolean isDynamicLinkDisplayed(WebDriver driver, String textLink) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LEFT_MENU, textLink);
		return findElementByXpath(driver, AbstractPageUI.DYNAMIC_LEFT_MENU, textLink).isDisplayed();
	}
	
	
	int longTimeout = 30;
	WebDriverWait explicitWait;
	WebElement element;
	Select select;
	JavascriptExecutor jsExecutor;
	Actions action;
}
