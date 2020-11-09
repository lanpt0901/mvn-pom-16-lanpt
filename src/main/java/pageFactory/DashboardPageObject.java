package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageObject extends AbstractPage{
	
	WebDriver driver;
	
	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CLASS_NAME, using = "formatted-header__title")
	private WebElement header;

	public String getHeaderSuccessPage() {
		waitForElementVisible(driver, header);
		return getTextOfElement(driver, header).trim();
	}

}
