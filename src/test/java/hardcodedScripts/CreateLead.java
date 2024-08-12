package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateLead {

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
		driver.findElement(By.xpath("//a[contains(@href,'Leads&action')]")).click();
		if(driver.getTitle().contains("Leads"))
		{
			System.out.println("Leads page is displayed");
		}
		else
		{
			System.out.println("Leads page is not displayed");
		}
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		WebElement pageheader= driver.findElement(By.xpath("//span[text()='Creating New Lead']"));
		if(pageheader.isDisplayed())
		{
			System.out.println("Creating  new Lead page is displayed");
		}
		else
		{
			System.out.println("Creating  new Lead page is not  displayed");
			
		}
		driver.findElement(By.name("lastname")).sendKeys("abc");
		driver.findElement(By.name("company")).sendKeys("xyz");
		driver.findElement(By.name("button")).click();
		driver.findElement(By.name("Duplicate")).click();
		String pagetitle=driver.findElement(By.xpath("//span[contains(text(),'Duplicating ')]")).getText();
		
		if(pagetitle.contains("Duplicating"))
		{
			System.out.println("Duplicate lead is created");
		}
		else
		{
			System.out.println("Duplicate lead is not created");
			
		}
		driver.findElement(By.name("button")).click();
		//driver.findElement(By.name("selectall")).click();
		driver.findElement(By.name("Delete")).click();
        driver.switchTo().alert().accept();
		Actions a1 = new Actions(driver);
		WebElement acc= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    a1.moveToElement(acc).perform();
	    driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	    driver.quit();

	}

}