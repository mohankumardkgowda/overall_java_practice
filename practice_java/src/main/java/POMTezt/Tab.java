package POMTezt;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import POMObject.Tab_Object;

public class Tab extends url{
//dont uze too complecated/advanced concept in framework	
	Tab_Object obj = new Tab_Object(driver);
	
	@Test
	public void testLogin() throws InterruptedException {
		
		obj.setUsername("Admin");
		obj.setPassword("admin123");
		obj.clickLoginButton();
	}

	@AfterTest
	public void tearDown() 
	{
		driver.close();
	}
}




//begining of framework(teztng, cucumber, datadriven, hybrid), pom iz not frameork it iz dezign pattern
//analyze AUT, filter tc for automation (bazed on preority),zanity, data driven, regrezzion,after it , all
//our tc 85automation-15%manual, in 85 if we automate all 85, tjen it iz 100% automation
//dezign and development
//execution, local, remotly
//maninatice