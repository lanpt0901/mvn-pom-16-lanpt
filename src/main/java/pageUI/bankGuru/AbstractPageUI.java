package pageUI.bankGuru;

public class AbstractPageUI {
	public static final String NEW_CUSTOMER_MENU = "//a[text()='New Customer']";
	public static final String EDIT_CUSTOMER_MENU = "//a[text()='Edit Customer']";
	public static final String DELETE_CUSTOMER_MENU = "//a[text()='Delete Customer']";
	public static final String NEW_ACCOUNT_MENU = "//a[text()='New Account']";
	
	public static final String DYNAMIC_MENU = "//span[text()='%s']";
	public static final String DYNAMIC_LEFT_MENU = "//a[text()='%s']";
	public static final String DYNAMIC_INPUT = "//input[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";
	public static final String DYNAMIC_AREA = "//textarea[@name='%s']";
	public static final String DYNAMIC_HEADING_PAGE = "//p[@class='heading3' and text()='%s']";
	public static final String DYNAMIC_TABLE_ROW_NAME = "//td[text()='%s']/following-sibling::td";
	
	
	
}
