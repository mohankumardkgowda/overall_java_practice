package liztener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(liztener.MyListener.class)
//we can trigger liztner witjout xml file, but not recommended, becauze we need to add given line in all teztz

public class liztner {

    public static WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void teztlink1() {
        try {
            WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
            WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
            WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            login.click();

        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void teztlink11() {
        try {
            WebElement username = driver.findElement(By.xpath("//input[@placeholder='Usernameuu']"));
            WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
            WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            login.click();

        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



