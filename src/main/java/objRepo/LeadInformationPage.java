package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInformationPage 
{

	// Declaration
	@FindBy(css = "span.dvHeaderText")
	private WebElement pageHeader;

	@FindBy(name = "Duplicate")
	private WebElement duplicateBTN;

	// Initialization
	public LeadInformationPage(WebDriver driver) {
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
	 * This method is used to click on duplicate button
	 */
	public void clickDuplicateBTN() {
		duplicateBTN.click();
	}
}
