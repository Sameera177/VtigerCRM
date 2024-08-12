package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class RetryTest 
{

	//@Test(retryAnalyzer = genericUtilities.RetryImplementation.class
	@Test
	public void demo()
	{
		Reporter.log("demo", true);
		//Assert.fail();
	}
}
