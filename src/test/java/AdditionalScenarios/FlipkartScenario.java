package AdditionalScenarios;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FlipkartScenario 
{
	@Test
	public void validateTotalItemsTest() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.name("q")).sendKeys("iphone");
		driver.findElement(By.className("_2iLD__")).click();
		
		int sum = 0;
		for(;;)
		{
			Thread.sleep(2000);
			List<WebElement> iphoneList = driver.findElements(By.className("KzDlHZ"));
			sum = sum + iphoneList.size();
			Thread.sleep(2000);
			try
			{
				WebElement nextBTN = driver.findElement(By.xpath("//span[text()='Next']"));
				nextBTN.click();
			}
			catch(NoSuchElementException e)
			{
				break;
			}
		}
		
		String header = driver.findElement(By.className("BUOuZu")).getText();
		String[] str = header.split(" ");
		int totalItems = 0 ;
		if(str[5].contains(","))
		{
			String[] s = str[5].split(",");
			String items = s[0].concat(s[1]);
			totalItems = Integer.parseInt(items);
		}
		else
		
			totalItems = Integer.parseInt(str[5]);
		
		if(sum == totalItems)
			System.out.println("Total number of items matched");
		else
			System.out.println("Mis-matched");
		
			driver.quit();
		}
	

	}


