package Assertions;

import org.testng.annotations.Test;

public class WhyNotIfElse 
{
	@Test
	public void demo() {
		String s1 = "Hello";
		String s2 = "Hi";
		
		if(s1.equals(s2))
			System.out.println("Pass");
		else
			System.out.println("Fail");
	}
}
