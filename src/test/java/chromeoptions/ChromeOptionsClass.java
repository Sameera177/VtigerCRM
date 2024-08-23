package chromeoptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ChromeOptionsClass 
{
	@Test
	public void startMaximizedBrowser() throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		
		Thread.sleep(2000);
		driver.quit();
		
	}
	
	
	@Test
	public void headlessExecution() throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		
		Thread.sleep(2000);
		driver.quit();
		
	}
	
	@Test
	public void incognitoExecution() throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		
		Thread.sleep(2000);
		driver.quit();
		
	}	
	
	
	@Test
	public void removeAutomatedBySoftwareBar() throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		Thread.sleep(2000);
		driver.quit();
		
	}
}
