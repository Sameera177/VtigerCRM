package genericUtilityImplementationScripts;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtillities.ExcelUtility;
import genericUtillities.IConstantPath;
import genericUtillities.JavaUtility;
import genericUtillities.PropertiesUtility;
import genericUtillities.WebDriverUtility;

public class CreateLead {

	public static void main(String[] args) 
	{
		
		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil= new JavaUtility();
		WebDriverUtility driverUtil= new WebDriverUtility();
		
		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);

		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));
		
		long time = (long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"),"long");
		driverUtil.waitTillElementFound(time);
		
		
		if(driver.getTitle().contains("Vtiger"))
		{
			System.out.println("Login page is displayed");
		}
		else
		{
			System.out.println("Login page is not displayed");
		}
		
		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).click();
		
		
		if(driver.getTitle().contains("Home"))
		{
			System.out.println("Home Page is displayed");
		}
		else
		{
			System.out.println("Home Page is not displayed");
		}
		driver.findElement(By.xpath("//a[contains(@href,'Leads&action')]")).click();
		if(driver.getTitle().contains("Leads"))
		{
			System.out.println("Leads page is displayed");
		}
		else
		{
			System.out.println("Leads page is not displayed");
		}
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		WebElement pageheader= driver.findElement(By.xpath("//span[text()='Creating New Lead']"));
		if(pageheader.isDisplayed())
		{
			System.out.println("Creating  new Lead page is displayed");
		}
		else
		{
			System.out.println("Creating  new Lead page is not  displayed");
			
		}
		
		Map<String, String> map = excel.readFromExcel("LeadsTestData", "Create and Duplicate Lead");
		
		driver.findElement(By.name("lastname")).sendKeys(map.get("Last Name"));
		driver.findElement(By.name("company")).sendKeys(map.get("Company"));
		driver.findElement(By.name("button")).click();
		driver.findElement(By.name("Duplicate")).click();
		String pagetitle=driver.findElement(By.xpath("//span[contains(text(),'Duplicating ')]")).getText();
		
		if(pagetitle.contains("Duplicating"))
		{
			System.out.println("Duplicate lead is created");
			excel.WriteToExcel("LeadsTestData", "Create and Duplicate Lead", "Pass");

		}
		else
		{
			System.out.println("Duplicate lead is not created");
			excel.WriteToExcel("LeadsTestData", "Create and Duplicate Lead", "fail");
		}
		
		driver.findElement(By.name("button")).click();
		//driver.findElement(By.name("Delete")).click();
		
		
excel.saveExcel(IConstantPath.EXCEL_PATH);
	
		
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverUtil.mouseHover(adminWidget);
	
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	
		driverUtil.quitAllWindows();

	}

}