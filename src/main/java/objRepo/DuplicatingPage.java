package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicatingPage 
{
	//Declaration
	@FindBy(css= "span.lvtHeaderText")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement leadLastNameTF;
	
	@FindBy(name ="company")
	private WebElement companyTF;
	
	@FindBy(xpath = "//input[contains(@title,'Save')]")
	private WebElement saveBTN;
	
	//Initialization
	public DuplicatingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Utilization

		/**
		 * This method fetches the page header
		 * 
		 * @return String
		 */
		public String getPageHeader() {
			return pageHeader.getText();
		}

		/**
		 * This method sets the lead name into the Lead Last Name text field
		 * 
		 * @param name
		 */
		public void setLeadLastName(String name) {
			leadLastNameTF.clear();
			leadLastNameTF.sendKeys(name);
		}

		/**
		 * This method sets the company name in Company text field
		 * 
		 * @param company
		 */
		public void setCompanyName(String company) {
			companyTF.clear();
			companyTF.sendKeys(company);
		}

		/**
		 * This method clicks on Save button
		 */
		public void clickSaveBTN() {
			saveBTN.click();
		}
}
	
