package genericUtilityImplementationScripts;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import genericUtillities.DataType;
import genericUtillities.ExcelUtility;
import genericUtillities.IConstantPath;
import genericUtillities.JavaUtility;
import genericUtillities.PropertiesUtility;
import genericUtillities.WebDriverUtility;

public class CreateOrganizationTest
{
	public static void main(String[] args)
	{
		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();
		
		
		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		
		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));
	
		
		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"),DataType.LONG);
		driverUtil.waitTillElementFound(time);
		if(driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();
	
		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();
	
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();
	
		
		driver.findElement(By.xpath("//a[contains(@href,'Accounts&action=index')]")).click();
	
		
		if(driver.getTitle().contains("Organizations"))
			System.out.println("Organizations Page is Displayed");
		else
			driverUtil.quitAllWindows();
	
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	
		
		WebElement pageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(pageHeader.isDisplayed())
			System.out.println("Creating New Organization Page is Displayed");
		else
			driverUtil.quitAllWindows();
	
		
		Map<String, String> map = excel.readFromExcel("OrganizationsTestData", "Create Organization");
	
		driver.findElement(By.name("accountname")).sendKeys(map.get("Organization Name"));	
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
	
		
		String newOrgPageHeader = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
		if(newOrgPageHeader.contains(map.get("Organization Name")))
			System.out.println("Organization created successfully");
		else
			driverUtil.quitAllWindows();
	
		
		driver.findElement(By.xpath("//input[@name='Delete']")).click();
		driverUtil.handleAlert("ok");
	
		
		if(driver.getTitle().contains("Organizations"))
		{
			System.out.println("Organizations Page is Displayed");
			excel.WriteToExcel("OrganizationsTestData", "Create Organization", "Pass");
		}
		else
		{
			driverUtil.quitAllWindows();
			excel.WriteToExcel("OrganizationsTestData", "Create Organization", "Fail");
		}
	
		
		excel.saveExcel(IConstantPath.EXCEL_PATH);
	
		
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src = 'themes/softed/images/user.PNG']"));
		driverUtil.mouseHover(adminWidget);
	
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	
		driverUtil.quitAllWindows();
	}

}
