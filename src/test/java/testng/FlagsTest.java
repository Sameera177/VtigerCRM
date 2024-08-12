package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class FlagsTest 
{
	@Test(priority = -1, invocationCount = 2)
	public void demo1() {
		Reporter.log("demo1", true);
	}
	
	@Test(priority = 1, enabled = false)
	public void demo2() {
		Reporter.log("demo2", true);
	}
	
	@Test(invocationCount = 3)
	public void demo3() {
		Reporter.log("demo3", true);
	}
	
	@Test(priority = 2, invocationCount = 2)
	public void demo4() {
		Reporter.log("demo4", true);
	}
	
	@Test(priority = -2, enabled = false)
	public void demo5() {
		Reporter.log("demo5", true);
	}
}
