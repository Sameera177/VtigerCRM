																																																																																																																																																																																																																																																																																																	package fitpeoScript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestScript 
{
	@Test
	public void fitPeoTest() throws InterruptedException, AWTException
	{
		SoftAssert soft = new SoftAssert();

		// Navigating to fitpeo.com
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://fitpeo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Click on Revenue Calculator
		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();

		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		WebElement sliderValue = driver.findElement(By.cssSelector("input.MuiInputBase-input"));

		// Scroll till slider value input text field
		Actions actions = new Actions(driver);
		actions.scrollToElement(sliderValue).perform();

		// Adjust slider to the value 820
		for (;;) 
		{
			if (slider.getAttribute("aria-valuenow").equals("820"))
				break;
			slider.sendKeys(Keys.ARROW_RIGHT);
		}

		// Clear the slider value input text field
		sliderValue.click();
		Robot robot = new Robot();
		for (int i = 0; i < 3; i++) 
		{
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		}

		Thread.sleep(2000);

		// Update the value to '560' in the slider value input text field
		sliderValue.sendKeys("" + Keys.NUMPAD5 + Keys.NUMPAD6 + Keys.NUMPAD0);

		// Validate if the slider is adjusted to the value entered in the input field
		// (560)
		soft.assertEquals(slider.getAttribute("aria-valuenow"), "560");

		// Stored all CPT codes in an ArrayList
		List<String> cptCodes = new ArrayList<>();
		cptCodes.add("CPT-99091");
		cptCodes.add("CPT-99453");
		cptCodes.add("CPT-99454");
		cptCodes.add("CPT-99474");

		// Select the check boxes of respective CPT codes
		for (String string : cptCodes) 
		{
			driver.findElement(By.xpath("//p[text()='" + string + "']/following-sibling::label//input")).click();
		}
		
		// Validate if total recurring reimbursement value is 110700
		WebElement totalRecurringReimbursement = driver
				.findElement(By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p"));
		soft.assertTrue(totalRecurringReimbursement.getText().contains("110700"));

		driver.quit();
		soft.assertAll();
	}
}