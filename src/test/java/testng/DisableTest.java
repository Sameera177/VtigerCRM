package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DisableTest {
	@Test(invocationCount = -1)
	public void demo1()
	{
		Reporter.log("demo1", true);
	}
	@Test(enabled = false)
	public void demo2()
	{
		Reporter.log("demo2", true);
	}
	@Test()
	public void demo3()
	{
		Reporter.log("demo3", true);
	}


}
