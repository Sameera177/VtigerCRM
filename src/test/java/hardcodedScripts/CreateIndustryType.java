package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateIndustryType {

	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		if(driver.getTitle().contains("Vtiger"))
		{
			System.out.println("Login page is displayed");
		}
		else
		{
			System.out.println("Login page is not displayed");
		}
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		if(driver.getTitle().contains("Home"))
		{
			System.out.println("Home is displayed");
		}
		else
		{
			System.out.println("Home is not displayed");
		}
		
		driver.findElement(By.xpath("//a[contains(@href,'Accounts&action=index')]")).click();
		if(driver.getTitle().contains("Organizations"))
		{
			System.out.println("Organizations page is displayed");
		}
		else
		{
			System.out.println("Organizations page is not displayed");
		}
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//span[text()='Creating New Organization']")).isDisplayed();
        driver.findElement(By.name("accountname")).sendKeys("xyz");
        WebElement industry= driver.findElement(By.name("industry"));
        Select s1 = new Select(industry);
        s1.selectByIndex(7);
        WebElement type = driver.findElement(By.name("accounttype"));
        Select s2 = new Select(type);
        s2.selectByIndex(5);
        
        driver.findElement(By.name("button")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]")).isDisplayed();
        driver.findElement(By.name("Delete")).click();
        driver.switchTo().alert().accept();
        if(driver.getTitle().contains("Organizations"))
		{
			System.out.println("Organizations page is displayed");
		}
		else
		{
			System.out.println("Organizations page is not displayed");
		}
        Actions a1 = new Actions(driver);
        
       WebElement acc= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
       a1.moveToElement(acc).perform();
       driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
       driver.quit();



	}

}