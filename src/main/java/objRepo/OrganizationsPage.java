package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * This class contains elements,locators and respective business libraries of Organizations Page
 */
public class OrganizationsPage {
		
	//DECLARATION
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgBTN;
	
	
	//INITIALIZATION
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//UTILIZATION
	public void clickCreateOrgBTN()
	{
		createOrgBTN.click();
	}

}
