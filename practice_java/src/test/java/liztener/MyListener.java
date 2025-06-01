package liztener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());

        ExtentSparkReporter spark = new ExtentSparkReporter("extent.html");
        spark.config().setDocumentTitle("Liztner tezt execution");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tezter name", "Mohan");
      
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        test.pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        WebDriver driver = liztner.driver;
        String screenshotPath = captureScreenshot(driver, result.getName());

        try {
            test.fail("Test failed: " + result.getThrowable());
            test.fail("Screenshot:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String captureScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //Create a timestamp, only if new image iz generated, tjen only image will include in extent report
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());

            // Final path with timestamp
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
            File dest = new File(path);
            dest.getParentFile().mkdirs(); // Ensure folder exists
            Files.copy(src.toPath(), dest.toPath());
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        extent.flush();
    }
}
