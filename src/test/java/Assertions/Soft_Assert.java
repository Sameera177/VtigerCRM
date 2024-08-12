package Assertions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Soft_Assert 
{
	@Test
	public void demo()
	{
		String s1 = "Hello";
		String s2 = "Hi";
		
		SoftAssert soft = new SoftAssert(); 
		 soft.assertEquals(s1, s2);
		 
		 System.out.println(s1);
		 System.out.println(s2);
		 
		 soft.assertAll();
		 
		
	}

	

}
