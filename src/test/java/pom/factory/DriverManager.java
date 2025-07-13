package pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public WebDriver initDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser");
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().cachePath("Drivers/").setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
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
