package practice_java;

import java.io.File;
import java.util.List;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practice{
	WebDriver driver;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("extent.html");
	//
	@BeforeTest
	void beforetezt() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		extent.attachReporter(spark);
		driver.get("https://www.saucedemo.com/");
		WebElement uzername=driver.findElement(By.xpath("//input[@placeholder='Username']"));
		WebElement pwd=driver.findElement(By.xpath("//input[@type='password']"));
		WebElement login=driver.findElement(By.xpath("//input[@type='submit']"));
		uzername.sendKeys("standard_user");
		pwd.sendKeys("secret_sauce");
		login.click();
	}
	@Test(priority = 1)//default =0, -1 allowed
	void teztlink() {
		ExtentTest test=extent.createTest("Verifying");
		test.info("begining");
		try {
			//Actions elementaction=new Actions(driver);
			//elementaction.moveToElement(Fashionelement).perform();
			
			WebElement menubutton= driver.findElement(By.xpath("//button[@type='button']"));
			menubutton.click();
		
			List<WebElement> links=driver.findElements(By.tagName("a"));
			System.out.println(links.size());
			for (int i = 0; i < links.size(); i++) {
		       // System.out.println("Text: " + links.get(i).getText());
				
		        System.out.println("URL: " + links.get(i).getAttribute("href"));
		    }
			test.pass("working");
		}catch(Exception e) {
			test.log(Status.FAIL, "tezt failed"+e.getMessage());
			
		}
	}
	@Test
	void teztimage() {
		ExtentTest test=extent.createTest("Verifying");
		test.info("begining");
		try {
			
			
			WebElement menubutton= driver.findElement(By.xpath("//button[@type='button']"));
			menubutton.click();
		
			
		    File filee=(File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    File dfile= new File("abc"+".png");
		    FileUtils.copyFile(filee, dfile);
			test.pass("working");
		}catch(Exception e) {
			test.log(Status.FAIL, "tezt failed"+e.getMessage());
			
		}
	}
	@Test
	void teztazzert() {
		ExtentTest test=extent.createTest("Verifying");
		test.info("begining");
		try {
			
			SoftAssert azzert= new SoftAssert();
			WebElement menubutton= driver.findElement(By.linkText("Sauce Labs Backpack"));
			String text=menubutton.getText();
			System.out.println(text);
			String title=driver.getTitle();
			System.out.println(title);
			azzert.assertEquals(text, title);
			System.out.println("abc");

//branch merge 
			azzert.assertAll();

			
			test.pass("working");
		}catch(Exception e) {
			test.log(Status.FAIL, "tezt failed"+e.getMessage());	
		}
	}
	@Test(testName = "amd")
	void teztzcroll() {
		ExtentTest test=extent.createTest("Verifying");
		test.info("begining");
		try {
			
			JavascriptExecutor jz = (JavascriptExecutor) driver;
			jz.executeScript("window.scrollBy(0,3000)");
			JavascriptExecutor jz1 = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.linkText("Sauce Labs Onesie"));
			jz1.executeScript("arguments[0].scrollIntoView(true);", element);
			test.pass("working");
		}catch(Exception e) {
			test.log(Status.FAIL, "tezt failed"+e.getMessage());
		//	
		}
	}
	@Test
	void actiontezt() {
		ExtentTest test=extent.createTest("Verifying");
		test.info("begining");
		try {
			WebElement menubutton= driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a"));
			Actions elementaction=new Actions(driver);
			Action actions=elementaction.moveToElement(menubutton).build();
			actions.perform();
			elementaction.contextClick(menubutton).perform();
			elementaction.doubleClick(menubutton).perform();

			test.pass("working");
		}catch(Exception e) {
			test.log(Status.FAIL, "tezt failed"+e.getMessage());
			
		}
	}

	
	
	@Test(dependsOnMethods = {"actiontezt", "teztzcroll"}, groups = {"abc", "xyz"})
	void actiondrag() {
		ExtentTest test=extent.createTest("Verifying");
		test.info("begining");
		try {
			
			
			WebElement menubutton= driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
			Select  zelect=new Select(menubutton);
			zelect.selectByIndex(3);

			
			
		 
			test.pass("working");
		}catch(Exception e) {
			test.log(Status.FAIL, "tezt failed"+e.getMessage());
			
		}
	}
	@AfterTest
	void aftertezt() {
		extent.flush();
	}
}
