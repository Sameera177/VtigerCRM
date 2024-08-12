package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains elements,locators and respective business libraries of Contacts Page
 */
public class ContactsPage 
{	
	//DECLARATION
		
		@FindBy(xpath = "//img[@alt='Create Contact...']")
		private WebElement createContactBTN;
		
		
	//INITIALIZATION
		public ContactsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		

	//UTILIZATION
	/**
	* This method clicks on the create contact button	
	*/
		public void clickCreateContactBTN()
		{
			createContactBTN.click();
		}

	}

