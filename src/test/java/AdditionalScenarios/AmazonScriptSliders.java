package AdditionalScenarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonScriptSliders {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles");
		driver.findElement(By.id("nav-search-submit-button")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//span[text()='Price']")));

		WebElement lower_bound_slider = driver
				.findElement(By.xpath("//input[@id='p_36/range-slider_slider-item_lower-bound-slider']"));
		WebElement upper_bound_slider = driver
				.findElement(By.xpath("//input[@id='p_36/range-slider_slider-item_upper-bound-slider']"));


		for (; ; ) {
			if(lower_bound_slider.getAttribute("aria-valuetext").contains("10,"))
				break;
			lower_bound_slider.sendKeys(Keys.ARROW_RIGHT);
		}
		
		Thread.sleep(2000);
		for (; ; ) {
			if(upper_bound_slider.getAttribute("aria-valuetext").contains("20,"))
				break;
			upper_bound_slider.sendKeys(Keys.ARROW_LEFT);
		}
		
		driver.findElement(By.xpath("//input[@aria-label='Go - Submit price range']")).click();
		
		Thread.sleep(3000);
		String first_item_price = driver.findElement(By.xpath("(//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'])[1]/../following-sibling::div/descendant::span[@class='a-price-whole']")).getText();
		System.out.println(first_item_price);
		
		driver.quit();		
	}

}