package testNGImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericUtillities.BaseClass;
import objRepo.CreateToDoPage;
import objRepo.EventInformationPage;


public class CreateEventTest extends BaseClass 
{

	@Test(groups = "events")
	public void createEventTest() {		
		CreateToDoPage createToDo = pageObjectManager.getCreateToDo();
		EventInformationPage eventInfo = pageObjectManager.getEventInfo();
		
		Map<String, String> map = excel.readFromExcel("EventsTestData", "Create New Event");
				home.selectFromQuickCreateDD(driverUtil, map.get("Quick Create"));

		jutil.waiting(3000);

		String subject = map.get("Subject") + jutil.generateRandomNum(100);
		createToDo.setSubject(subject);
		createToDo.clickStartDateWidget();

		createToDo.datePicker(jutil, driverUtil, map.get("Start Date"));
		jutil.waiting(2000);

		createToDo.clickDueDateWidget();
		createToDo.datePicker(jutil, driverUtil, map.get("Due Date"));

		createToDo.clickSaveBTN();
		
		soft.assertTrue(eventInfo.getPageHeader().contains(subject));
		if (eventInfo.getPageHeader().contains(subject)) 
			excel.WriteToExcel("EventsTestData", "Create New Event", "Pass");
		else
			excel.WriteToExcel("EventsTestData", "Create New Event", "Fail");

		eventInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
		soft.assertAll();
	}

}