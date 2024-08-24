package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;
// it is a sample test
public class SampleTest 
{
	@Test
	public void demo()
	{
		Reporter.log("Hello World!", true);
	}

}
