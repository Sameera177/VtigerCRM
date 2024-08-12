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

public class CreateEventTest {

	public static void main(String[] args) {
		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();

		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);

		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));

		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"), DataType.LONG);
		driverUtil.waitTillElementFound(time);

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readFromExcel("EventsTestData", "Create New Event");
		WebElement quickCreateDD = driver.findElement(By.id("qccombo"));
		driverUtil.handleDropdown(quickCreateDD, map.get("Quick Create"));

		jutil.waiting(3000);

		String subject = map.get("Subject") + jutil.generateRandomNum(100);
		driver.findElement(By.name("subject")).sendKeys(subject);
		driver.findElement(By.id("jscal_trigger_date_start")).click();

		String[] startDate = jutil.splitString(map.get("Start Date"), "-");
		int reqStartYear = (Integer) jutil.convertStringToAnyDataType(startDate[0], DataType.INT);
		String reqStartDate = startDate[2];
		int reqStartMonth = jutil.convertMonthToInt(startDate[1]);

		String currentMonthYear = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		String[] str = jutil.splitString(currentMonthYear, ", ");
		int currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], DataType.INT);

		while (currentYear < reqStartYear) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']"))
					.click();

			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], DataType.INT);
		}

		int currentMonth = jutil.convertMonthToInt(str[0]);

		while (currentMonth < reqStartMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='›']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		while (currentMonth > reqStartMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='‹']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		driver.findElement(By.xpath(
				"//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='" + reqStartDate + "']"))
				.click();

		driver.findElement(By.id("jscal_trigger_due_date")).click();

		String[] dueDate = jutil.splitString(map.get("Due Date"), "-");
		int reqEndYear = (Integer) jutil.convertStringToAnyDataType(dueDate[0], DataType.INT);
		String reqEndDate = dueDate[2];
		int reqEndMonth = jutil.convertMonthToInt(dueDate[1]);

		currentMonthYear = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		str = jutil.splitString(currentMonthYear, ", ");
		currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], DataType.INT);

		while (currentYear < reqEndYear) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']"))
					.click();

			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], DataType.INT);
		}

		currentMonth = jutil.convertMonthToInt(str[0]);

		while (currentMonth < reqEndMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='›']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);

		}

		while (currentMonth > reqEndMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='‹']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		driver.findElement(By.xpath(
				"//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='" + reqEndDate + "']"))
				.click();

		driver.findElement(By.xpath("//input[@value='  Save']")).click();

		String newEventPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (newEventPageHeader.contains(subject)) {
			System.out.println("Event Created");
			excel.WriteToExcel("EventsTestData", "Create New Event", "Pass");
		} else {
			System.out.println("Event Not Created");
			excel.WriteToExcel("EventsTestData", "Create New Event", "Fail");
		}

		excel.saveExcel(IConstantPath.EXCEL_PATH);
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverUtil.mouseHover(adminWidget);

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		excel.closeExcel();

		driverUtil.quitAllWindows();
	}

}