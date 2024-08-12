package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginScriptUsingPropertiesFile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");
		Properties property = new Properties();
		property.load(fis);
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(property.getProperty("url"));
		
		long time = Long.parseLong(property.getProperty("timeouts"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		
		driver.findElement(By.name("user_name")).sendKeys(property.getProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(property.getProperty("password"));
		driver.findElement(By.id("submitButton")).submit();
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Login Success");
		else
			System.out.println("Login Failed");
		
		driver.quit();
	}

}