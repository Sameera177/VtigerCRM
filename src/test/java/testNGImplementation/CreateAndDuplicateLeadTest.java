package testNGImplementation;


import java.util.Map;

import org.testng.annotations.Test;

import genericUtillities.BaseClass;
import genericUtillities.TabNames;
import objRepo.CreatingNewLeadPage;
import objRepo.DuplicatingPage;
import objRepo.LeadInformationPage;
import objRepo.LeadsPage;

//This test verifies if user is able to create and duplicate leads
public class CreateAndDuplicateLeadTest extends BaseClass {

	@Test(groups = "leads")
	public void createAndDuplicateLeadTest() {
		LeadsPage leads = pageObjectManager.getLead();
		CreatingNewLeadPage createLead = pageObjectManager.getCreateLead();
		DuplicatingPage duplicateLead = pageObjectManager.getDuplicateLead();
		LeadInformationPage leadInfo = pageObjectManager.getLeadInfo();

		home.clickRequiredTab(driverUtil, TabNames.LEADS);

		soft.assertTrue(driver.getTitle().contains("Leads"));

		leads.clickCreateLeadBTN();

		soft.assertTrue(createLead.getPageHeader().equalsIgnoreCase("creating new lead"));
			
		Map<String, String> map = excel.readFromExcel("LeadsTestData", "Create and Duplicate Lead");
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createLead.setLeadLastName(lastName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSaveBTN();

		soft.assertTrue(leadInfo.getPageHeader().contains(lastName));
			
		leadInfo.clickDuplicateBTN();
		soft.assertTrue(duplicateLead.getPageHeader().contains("Duplicating"));
			
		String newLastName = map.get("New Last Name") + jutil.generateRandomNum(100);
		duplicateLead.setLeadLastName(newLastName);
		duplicateLead.clickSaveBTN();

		soft.assertTrue(leadInfo.getPageHeader().contains(newLastName));
		if(leadInfo.getPageHeader().contains(newLastName)) 
			excel.WriteToExcel("LeadsTestData", "Create and Duplicate Lead", "Pass");
		else 
			excel.WriteToExcel("LeadsTestData", "Create and Duplicate Lead", "Fail");
		soft.assertAll();
	}

}