package POMObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tab_Object {
    WebDriver driver;

    // Constructor to initialize driver
    public Tab_Object(WebDriver driver) {
        this.driver = driver;
      
    }
    WebDriverWait wait;

   
    // Locators
    By username = By.xpath("//input[@name='username']");//type= By
    By password = By.xpath("//input[@name='password']");
    By loginButton = By.xpath("//button[@type='submit']");

    // Actions
    public void setUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void setPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
