package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginScript 
{	
	public static void main(String[] args) throws InterruptedException
	{
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://demoapp.skillrary.com/login.php?type=login");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	LoginPage login = new LoginPage(driver);
	login.setEmailTF("admin");
	login.setPasswordTF("admin");
	login.clickKeepMeLoggedInCB();
	login.clickLoginBTN();
	
	Thread.sleep(2000);
	driver.close();
	
	
	}


}
