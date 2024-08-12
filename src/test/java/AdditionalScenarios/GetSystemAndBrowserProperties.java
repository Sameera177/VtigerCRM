package AdditionalScenarios;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GetSystemAndBrowserProperties 
{
	public static void main(String[] args)
	{
		System.getProperties().list(System.out);
		System.out.println("-----------------");
		RemoteWebDriver driver = new ChromeDriver();
		Capabilities cap = driver.getCapabilities();
		
		System.out.println(cap.getBrowserName());
		System.out.println(cap.getBrowserVersion());
		
		driver.quit();
	}

}
