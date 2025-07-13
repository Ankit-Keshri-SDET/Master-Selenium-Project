package pom.factory;

import constants.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static constants.BrowserType.CHROME;
import static constants.BrowserType.FIREFOX;

public class DriverManager {

    public WebDriver initDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser");
        switch (BrowserType.valueOf(browser)) {
            case CHROME:
                WebDriverManager.chromedriver().cachePath("Drivers/").setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("Drivers/").setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("INVALID BROWSER .." + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
