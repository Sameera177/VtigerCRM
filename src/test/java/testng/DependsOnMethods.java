package testng;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DependsOnMethods 
{
	@Test(dependsOnMethods = "tc2")
	public void tc1() {
		Reporter.log("tc1", true);
	}
	
	@Test
	public void tc2() {
		Assert.fail();
		Reporter.log("tc2", true);
	}
	
	@Test(dependsOnMethods = "tc1", alwaysRun = true)
	public void tc3() {
		Reporter.log("tc3", true);
	}
}
