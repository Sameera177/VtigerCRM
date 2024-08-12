package hardcodedScripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithExistingOrgTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driver.quit();

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driver.quit();

		driver.findElement(By.xpath("//a[contains(@href,'Contacts&action=index')]")).click();
		if (driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page Displayed");
		else
			driver.quit();
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		WebElement pageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (pageHeader.isDisplayed())
			System.out.println("Creating New Contact Page is Displayed");
		else
			driver.quit();

		driver.findElement(By.name("lastname")).sendKeys("XYZ");
		driver.findElement(By.xpath("//img[contains(@onclick,'Accounts')]")).click();

		String parentID = driver.getWindowHandle();
		Set<String> windowIDs = driver.getWindowHandles();

		for (String id : windowIDs) {
			driver.switchTo().window(id);
		}

		driver.findElement(By.xpath("//a[text()='TYT']")).click();
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();

		String newContactPageHeader = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
		if (newContactPageHeader.contains("XYZ"))
			System.out.println("Contact created successfully");
		else
			driver.quit();

		
		driver.findElement(By.xpath("//input[@name='Delete']")).click();
		driver.switchTo().alert().accept();
		
		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page is Displayed");
		else
			driver.quit();
		
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(adminWidget).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		driver.quit();
	}

}
