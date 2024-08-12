package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtillities.DataType;
import genericUtillities.JavaUtility;
import genericUtillities.WebDriverUtility;

/**
 * This class contains elements, locators and respective business libraries of Create To Do Page
 */
public class CreateToDoPage 
{
	
	//Declaration
	@FindBy(name="subject")
	private WebElement subjectTF;
	
	@FindBy(id="jscal_trigger_date_start")
	private WebElement startDateWidget;
	
	@FindBy(xpath="//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")
	private WebElement calendarTitle;
	
	private String calendarCommonPath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='%s']";
	
	@FindBy(id = "jscal_trigger_due_date")
	private WebElement dueDateWidget;
	
	@FindBy(xpath= "//input[@value='  Save']")
	private WebElement saveBTN;
	
	//Initialization
	public CreateToDoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	
	/**
	 * This method is used to set the subject of the event
	 * @param subject
	 */
	public void setSubject(String subject)
	{
		subjectTF.sendKeys(subject);
	}
	
	/**
	 * This method clicks on the start date Calendar Widget
	 */
	public void clickStartDateWidget()
	{
		startDateWidget.click();
	}
	
	/**
	 * This method clicks on the due date calendar widget
	 */
	public void clickDueDateWidget()
	{
		dueDateWidget.click();
	}

/**
 * This method is used to
 * @param jutil
 * @param driverUtil
 * @param reqDate
 */
	public void datePicker(JavaUtility jutil, WebDriverUtility driverUtil, String reqDate)
	{
		String[] startDate = jutil.splitString(reqDate, "-");
		int reqStartYear = (Integer) jutil.convertStringToAnyDataType(startDate[0], DataType.INT);
		String reqStartDate = startDate[2];
		int reqStartMonth = jutil.convertMonthToInt(startDate[1]);

		String currentMonthYear = calendarTitle.getText();
		String[] str = jutil.splitString(currentMonthYear, ", ");
		int currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], DataType.INT);

		while (currentYear < reqStartYear) 
		{
			driverUtil.convertDynamicXpathToWebElement(calendarCommonPath, "»").click();
			currentMonthYear =calendarTitle.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], DataType.INT);
		}

		int currentMonth = jutil.convertMonthToInt(str[0]);

		while (currentMonth < reqStartMonth) 
		{
			driverUtil.convertDynamicXpathToWebElement(calendarCommonPath, "›").click();
			currentMonthYear = calendarTitle.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		while (currentMonth > reqStartMonth) 
		{
			driverUtil.convertDynamicXpathToWebElement(calendarCommonPath, "‹").click();
			currentMonthYear = calendarTitle.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		driverUtil.convertDynamicXpathToWebElement(calendarCommonPath, reqStartDate).click();
	}
	
	/**
	 * This method clicks on Save Button
	 */
	public void clickSaveBTN() 
	{
		saveBTN.click();
	}
}
