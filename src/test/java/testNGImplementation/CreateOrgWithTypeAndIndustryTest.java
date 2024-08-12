package testNGImplementation;


import java.util.Map;

import org.testng.annotations.Test;

import genericUtillities.BaseClass;
import genericUtillities.TabNames;
import objRepo.CreatingNewOrganizationPage;
import objRepo.OrganizationInformationPage;
import objRepo.OrganizationsPage;

public class CreateOrgWithTypeAndIndustryTest extends BaseClass
{
	@Test(groups = "organizations")

	public void createOrgWithTypeAndIndustryTest()
	{
	
			OrganizationsPage organization = pageObjectManager.getOrganization();
			CreatingNewOrganizationPage createOrg = pageObjectManager.getCreateOrg();
			OrganizationInformationPage orgInfo = pageObjectManager.getOrgInfo();

			home.clickRequiredTab(driverUtil, TabNames.ORGANIZATIONS);

			soft.assertTrue(driver.getTitle().contains("Organizations"));

			organization.clickCreateOrgBTN();

			soft.assertTrue(createOrg.getPageHeader().equalsIgnoreCase("creating new organization"));
				

			Map<String, String> map = excel.readFromExcel("OrganizationsTestData", "Create Organization With Industry And Type");
			String orgName = map.get("Organization Name") + jutil.generateRandomNum(100);
			createOrg.setOrganizationName(orgName);
			createOrg.selectFromIndustryDD(driverUtil, map.get("Industry"));
			createOrg.selectFromTypeDD(driverUtil, map.get("Type"));
			createOrg.clicksaveBTN();

			soft.assertTrue(orgInfo.getPageHeader().contains(orgName));


			orgInfo.clickDeleteBTN();
			driverUtil.handleAlert("ok");

			soft.assertTrue(driver.getTitle().contains("Organizations"));
			if (driver.getTitle().contains("Organizations")) 
			
				excel.WriteToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Pass");
			 else 
		
				excel.WriteToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Fail");
			soft.assertAll();

			
		}

}