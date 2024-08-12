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
import objRepo.ContactInformationPage;
import objRepo.ContactsPage;
import objRepo.CreatingNewContactPage;
import objRepo.HomePage;
import objRepo.LoginPage;

public class CreateContactWithExistingOrgTest
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
			
			long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"), 
					
					DataType.LONG);
			driverUtil.waitTillElementFound(time);
			
			
			LoginPage login = new LoginPage(driver);
			HomePage home = new HomePage(driver);
			ContactsPage  contact = new ContactsPage(driver);
			CreatingNewContactPage createContact= new CreatingNewContactPage(driver);
			ContactInformationPage contactInfo = new ContactInformationPage(driver);
			

			
			if (driver.getTitle().contains("vtiger CRM"))
				System.out.println("Login Page Displayed");
			else
				driverUtil.quitAllWindows();

login.logintoVtiger(propertyUtil.readFromProperties("username"),propertyUtil.readFromProperties("password"));
			
			if (driver.getTitle().contains("Home"))
				System.out.println("Home Page is Displayed");
			else
				driverUtil.quitAllWindows();
			
			home.clickRequiredTab(driverUtil, TabNames.CONTACTS);
			if (driver.getTitle().contains("Contacts"))
				System.out.println("Contacts Page Displayed");
			else
				driverUtil.quitAllWindows();
			
			
			contact.clickCreateContactBTN();

			
			if (createContact.getPageHeader().equalsIgnoreCase("Creating new contact"))
				System.out.println("Creating New Contact Page is Displayed");
			else
				driverUtil.quitAllWindows();
			
			
			Map<String, String> map = excel.readFromExcel("ContactsTestData", "Create Contact With Organization");
			
			createContact.setContactLastName(map.get("Last Name"));
			createContact.selectExistingOrganization(driverUtil, map.get("Organization Name"));
			

			createContact.clicksaveBTN();
			
			if (contactInfo.getPageHeader().contains(map.get("Last Name")))				
				System.out.println("Contact created successfully");
			else
				driverUtil.quitAllWindows();
			
			contactInfo.clickDeleteBTN();
			driverUtil.handleAlert("ok");
			
			if(driver.getTitle().contains("Contacts")) 
			{
				System.out.println("Contacts Page is Displayed");
				excel.WriteToExcel("ContactsTestData", "Create Contact With Organization", "Pass");
			}
			else 
			{
				driverUtil.quitAllWindows();
				excel.WriteToExcel("ContactsTestData", "Create Contact With Organization", "Fail");
			}
			
			excel.saveExcel(IConstantPath.EXCEL_PATH);
			home.signOutOfVtiger(driverUtil);
			
			excel.closeExcel();
			driverUtil.quitAllWindows();
		}

	}