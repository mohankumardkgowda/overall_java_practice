package pqrallel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dataprovider {

    WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            System.out.println("Browser not supported: " + browser);
            return;
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }


    @DataProvider(name = "dp", indices = {0,1})//indicez execute only index 0 and 1
    public Object[][] getData() {
        return new Object[][] {
            {"standard_user", "secret_sauce"},
            {"admin_user", "secret_sauce"},
            {"new_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "dp")
    public void testLogin(String usernameStr, String passwordStr) throws InterruptedException {//parameter uzing zame clazz
        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        username.clear();
        username.sendKeys(usernameStr);
        password.clear();
        password.sendKeys(passwordStr);
        loginButton.click();

        Thread.sleep(2000); // wait for page transition

        boolean loginSuccess;
        try {
            loginSuccess = driver.findElement(By.id("react-burger-menu-btn")).isDisplayed();
        } catch (Exception e) {
            loginSuccess = false;
        }

        System.out.println("Login result for user '" + usernameStr + "': " + (loginSuccess ? "Success" : "Failure"));

        if (loginSuccess) {
            try {
                driver.findElement(By.id("react-burger-menu-btn")).click();
                Thread.sleep(1000); // wait for sidebar animation
                driver.findElement(By.id("logout_sidebar_link")).click();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Logout failed for user: " + usernameStr);
            }
        } else {
            driver.navigate().refresh();
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
