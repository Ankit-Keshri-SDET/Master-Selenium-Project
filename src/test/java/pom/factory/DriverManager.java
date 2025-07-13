package pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public WebDriver initDriver() {
//        WebDriverManager.chromedriver().cachePath("Drivers").setup();
//        WebDriver driver = new ChromeDriver();

        // Section 17 - Multiple Browser Support
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
