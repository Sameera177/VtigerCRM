package chromeoptions;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class HandleAnyNotifications 
{
	@Test
	public void handleNotifications() throws InterruptedException
	{
		HashMap<String, Integer> contentSettings = new HashMap<>();
		HashMap<String, Object> profile = new HashMap<>();
		HashMap<String, Object> preference = new HashMap<>();
		
		contentSettings.put("media_stream", 1);
		contentSettings.put("notifications", 1);
		contentSettings.put("geolocation", 2);
		profile.put("managed_default_content_settings", contentSettings);
		preference.put("profile", profile);
		
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preference);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://webcamtests.com/check");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("webcam-launcher")).click();
		
		Thread.sleep(4000);
		driver.quit();
	}

}
