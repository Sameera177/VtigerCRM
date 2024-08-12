package pomImplementationScripts;


import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericUtillities.DataType;
import genericUtillities.ExcelUtility;
import genericUtillities.IConstantPath;
import genericUtillities.JavaUtility;
import genericUtillities.PropertiesUtility;
import genericUtillities.TabNames;
import genericUtillities.WebDriverUtility;
import objRepo.CreatingNewOrganizationPage;
import objRepo.HomePage;
import objRepo.LoginPage;
import objRepo.OrganizationInformationPage;
import objRepo.OrganizationsPage;

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
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationsPage organization = new OrganizationsPage(driver);
		CreatingNewOrganizationPage createOrg = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
		
		if(driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();
	
	login.logintoVtiger(propertyUtil.readFromProperties("username"), propertyUtil.readFromProperties("password"));
	
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();
	
		
	home.clickRequiredTab(driverUtil, TabNames.ORGANIZATIONS);
	
		
		if(driver.getTitle().contains("Organizations"))
			System.out.println("Organizations Page is Displayed");
		else
			driverUtil.quitAllWindows();
	
		
		organization.clickCreateOrgBTN();
	
		

		if (createOrg.getPageHeader().equalsIgnoreCase("creating new organization"))
			System.out.println("Creating New Organization Page is Displayed");
		else
			driverUtil.quitAllWindows();
	
		
		Map<String, String> map = excel.readFromExcel("OrganizationsTestData", "Create Organization");
	
		createOrg.setOrganizationName(map.get("Organization Name"));
		createOrg.clicksaveBTN();
	
		if(orgInfo.getPageHeader().contains(map.get("Organization Name")))
			System.out.println("Organization created successfully");
		else
			driverUtil.quitAllWindows();
	
		
		orgInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
		
		if (driver.getTitle().contains("Organizations")) {
			System.out.println("Organizations Page is Displayed");
			excel.WriteToExcel("OrganizationsTestData", "Create Organization", "Pass");
		} else {
			driverUtil.quitAllWindows();
			excel.WriteToExcel("OrganizationsTestData", "Create Organization", "Fail");
		}

		excel.saveExcel(IConstantPath.EXCEL_PATH);

		home.signOutOfVtiger(driverUtil);
		excel.closeExcel();
		driverUtil.quitAllWindows();
	}

}
