package testNGImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericUtillities.BaseClass;
import genericUtillities.TabNames;
import objRepo.ContactInformationPage;
import objRepo.ContactsPage;
import objRepo.CreatingNewContactPage;


public class CreateContactWithExistingOrgTest extends BaseClass
{
		@Test(groups = "contacts")
		public void createContact()
		{
			ContactsPage contact = pageObjectManager.getContact();
			CreatingNewContactPage createContact = pageObjectManager.getCreateContact();
			ContactInformationPage contactInfo = pageObjectManager.getContactInfo();
			
			
			home.clickRequiredTab(driverUtil, TabNames.CONTACTS);
			soft.assertTrue(driver.getTitle().contains("Contacts"));
			
			contact.clickCreateContactBTN();

			
			soft.assertTrue(createContact.getPageHeader().equalsIgnoreCase("Creating new contact"));
			
			Map<String, String> map = excel.readFromExcel("ContactsTestData", "Create Contact With Organization");
			
			createContact.setContactLastName(map.get("Last Name"));
			createContact.selectExistingOrganization(driverUtil, map.get("Organization Name"));
			
			createContact.clicksaveBTN();
			
			
			soft.assertTrue(contactInfo.getPageHeader().contains(map.get("Last Name")));
				
			contactInfo.clickDeleteBTN();
			driverUtil.handleAlert("ok");
			
			soft.assertTrue(driver.getTitle().contains("Contacts")); 
			if(driver.getTitle().contains("Contacts")) 
			
				excel.WriteToExcel("ContactsTestData", "Create Contact With Organization", "Pass");
			else 
			
				driverUtil.quitAllWindows();
				excel.WriteToExcel("ContactsTestData", "Create Contact With Organization", "Fail");
	
			
			soft.assertAll();
		}

	}