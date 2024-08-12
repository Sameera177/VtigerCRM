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
import objRepo.CreatingNewLeadPage;
import objRepo.DuplicatingPage;
import objRepo.HomePage;
import objRepo.LeadInformationPage;
import objRepo.LeadsPage;
import objRepo.LoginPage;

public class CreateLead {

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
		LeadsPage leads = new LeadsPage(driver);
		CreatingNewLeadPage createLead = new CreatingNewLeadPage(driver);
		DuplicatingPage duplicateLead = new DuplicatingPage(driver);
		LeadInformationPage leadInfo = new LeadInformationPage(driver);
		
		
		if(driver.getTitle().contains("vtiger CRM"))
		{
			System.out.println("Login page is displayed");
		}
		else
			driverUtil.quitAllWindows();
		
		login.logintoVtiger(propertyUtil.readFromProperties("username"), propertyUtil.readFromProperties("password"));		
		
		if(driver.getTitle().contains("Home"))
		{
			System.out.println("Home Page is displayed");
		}
		else
			driverUtil.quitAllWindows();
		
		home.clickRequiredTab(driverUtil, TabNames.LEADS);
		
		if(driver.getTitle().contains("Leads"))
		{
			System.out.println("Organizations page is displayed");
		}
		else
			driverUtil.quitAllWindows();
		
		leads.clickCreateLeadBTN();
		
		if (createLead.getPageHeader().equalsIgnoreCase("creating new lead"))
			System.out.println("Creating New lead Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readFromExcel("LeadsTestData", "Create and Duplicate Lead");
		
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		
		createLead.setLeadLastName(lastName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSaveBTN();
		
		
		if (leadInfo.getPageHeader().contains(lastName))
			System.out.println("Lead created successfully");
		else
			System.out.println("Lead created not successfully");

		leadInfo.clickDuplicateBTN();
		if (duplicateLead.getPageHeader().contains("Duplicating"))
			System.out.println("Duplicating lead page displayed");
		else
			driverUtil.quitAllWindows();

		String newLastName = map.get("New Last Name") + jutil.generateRandomNum(100);
		duplicateLead.setLeadLastName(newLastName);
		duplicateLead.clickSaveBTN();

		if (leadInfo.getPageHeader().contains(newLastName)) 
		{
			System.out.println("Duplicate Lead created successfully");
			excel.WriteToExcel("LeadsTestData", "Create and Duplicate Lead", "Pass");
		} 
		else 
		{
			driverUtil.quitAllWindows();
			excel.WriteToExcel("LeadsTestData", "Create and Duplicate Lead", "Fail");
		}

		excel.saveExcel(IConstantPath.EXCEL_PATH);

		home.signOutOfVtiger(driverUtil);
		excel.closeExcel();
		driverUtil.quitAllWindows();
	}


}