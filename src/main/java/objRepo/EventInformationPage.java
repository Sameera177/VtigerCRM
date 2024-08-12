package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * This class contains elements, locators and respective business libraries of Contact Information Page 
 */
public class EventInformationPage
{
	//Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
    private WebElement pageHeader;

	@FindBy(xpath="//input[@name='Delete']")
	private WebElement deleteBTN;

	
	//Initialization
	public EventInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	/**
	 * This method returns page Header
	 * @return String
	 */
	public String getPageHeader()
	{
		return pageHeader.getText();
	}
	
	/**
	 * This method clicks on delete button
	 */
	public void clickDeleteBTN()
	{
		deleteBTN.click();
	}

	
}
