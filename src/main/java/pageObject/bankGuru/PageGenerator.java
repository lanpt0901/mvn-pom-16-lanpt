package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

public class PageGenerator {

	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver) ;
	}
	
	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver) ;
	}
	
	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPageObject(driver) ;
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver) ;
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver) ;
	}

	public static CustomerRegistedMessagePageObject getCustomerRegMsgPageObject(WebDriver driver) {
		return new CustomerRegistedMessagePageObject(driver) ;
	}

	public static FirstEditCustomerPageObject getFirstEditCustomerPage(WebDriver driver) {
		return new FirstEditCustomerPageObject(driver);
	}
	
	public static CustomerUpdateMsgPageObject getCustomerUpdateMsgPageObject(WebDriver driver) {
		return new CustomerUpdateMsgPageObject(driver) ;
	}

}
