package base;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pom.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);
//        if (browser == null) browser = "CHROME";
        setDriver(new DriverManager().initDriver(browser));
    }

    @AfterMethod
    public void quitDriver() {
        getDriver().quit();
    }
}
