package base;

import org.testng.annotations.Parameters;
import pom.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser) {
        driver = new DriverManager().initDriver(browser);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
