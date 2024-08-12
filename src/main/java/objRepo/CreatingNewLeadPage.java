package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewLeadPage 
{
	//Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement leadLastNameTF;
	
	@FindBy(name = "company")
	private WebElement companyTF;
	
	@FindBy(xpath="//input[contains(@title,'Save')]")
	private WebElement saveBTN;
	
	//Initialization
	public CreatingNewLeadPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public String getPageHeader()
	{
		return pageHeader.getText();
	}
	
	public void setLeadLastName(String name)
	{
		leadLastNameTF.sendKeys(name);
	}
	
	public void setCompanyName(String company)
	{
		companyTF.sendKeys(company);
	}
	
	public void clickSaveBTN()
	{
		saveBTN.click();
	}
}
