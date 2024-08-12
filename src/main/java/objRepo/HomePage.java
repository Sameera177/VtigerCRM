package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtillities.TabNames;
import genericUtillities.WebDriverUtility;


/**
 * This class contains elements,locators and respective business libraries of Home page
 */
public class HomePage 
{
	
	//Declaration
	
	private String commonPathForTabs ="//a[contains(@href, '%s&action=index')]";
	
//	@FindBy(xpath="//a[contains(@href, 'Accounts&action=index')]")
//			private WebElement organizationsTab;
//	
//	@FindBy(xpath="//a[contains(@href, 'Contacts&action=index')]")
//	private WebElement contactsTab;
//	
//	@FindBy(xpath="//a[contains(@href, 'Leads&action=index')]")
//	private WebElement leadsTab;
//	
	@FindBy(id = "qccombo")
	private WebElement quickCreateDD;
	
	@FindBy(xpath = "//img[@src = 'themes/softed/images/user.PNG']")
	private WebElement adminWidget;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signOutLink;
	
	
	//Initialization
			public HomePage(WebDriver driver)
			{
				PageFactory.initElements(driver,this);
			}
			
	//Utilization
	/**
	 * This method clicks on specified Tab
	 * @param driverUtil
	 * @param tabName
	 */
			public void clickRequiredTab(WebDriverUtility driverUtil, TabNames tabName)
			{
				driverUtil.convertDynamicXpathToWebElement(commonPathForTabs, tabName.getTabName()).click();
			}
			
//	/**
//	 * This method clicks on Organizations Tab
//	 */
//	
//			public void clickOrganizationsTab()
//			{
//				organizationsTab.click();
//			}
//	/**
//	 * This method clicks on Contacts Tab
//	 */
//			public void clickContactsTab()
//			{
//				contactsTab.click();
//			}
//	/**
//	 * This method clicks on Leads Tab
//	 */			
//			public void clickLeadsTab()
//			{
//				leadsTab.click();
	/**
	 * This method is used to sign out of vtiger
	 * @param driverUtil 
	 */
			public void signOutOfVtiger(WebDriverUtility driverUtil)
			{
				driverUtil.mouseHover(adminWidget);
				signOutLink.click();
			}
	/**
	 * This method is used to select an option from quick create dropdown
	 * @param driverUtil
	 * @param value
	 */
			public void selectFromQuickCreateDD(WebDriverUtility driverUtil, String value)
			{
				driverUtil.handleDropdown(quickCreateDD, value);
			}
		
}
