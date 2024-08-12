package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtillities.WebDriverUtility;

/**
 * This class contains elements, locators and respective business libraries of Creating New Contact Page 
 */
public class CreatingNewContactPage
{

	//DECLARATION
	@FindBy(xpath= "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement contactLastNameTF;
	
	@FindBy(xpath="//input[contains(@title,'Save')]")
	private WebElement saveBTN;
	
	@FindBy(xpath="//img[contains(@onclick,'Accounts')]")
	private WebElement organizationPlusBTN;
	
	private String organizationPath = "//a[text()='%s']";
	
	//Initialization
	public CreatingNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	
	/**
	 * This method fetches the page header
	 * @return
	 */
	public String getPageHeader()
	{
		return pageHeader.getText();
	}
	
	/**
	 * This method sets the contact name into the contact last name text field
	 * @param name
	 */
	public void setContactLastName(String name)
	{
		contactLastNameTF.sendKeys(name);
	}
	
	/**
	 * This method clicks on Save Button
	 */
	public void clicksaveBTN() 
	{
		saveBTN.click();
	}
	
	
	public void selectExistingOrganization(WebDriverUtility driverUtil, String orgName)
	{
		organizationPlusBTN.click();
		driverUtil.switchToWindow("Accounts");
		driverUtil.convertDynamicXpathToWebElement(organizationPath,orgName).click();
		driverUtil.switchToWindow("Contacts");
	
	}

	

	
}
