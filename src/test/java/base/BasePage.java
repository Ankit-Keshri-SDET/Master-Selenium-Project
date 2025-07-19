package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void load(String endpoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endpoint);
    }

    public void waitForOverlaysToDissapear(List<WebElement> overlayElements) {
        if (!overlayElements.isEmpty()) {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.invisibilityOfAllElements(overlayElements));

        } else {
            System.out.println("OVERLAYS NOT FOUND ...");
        }
    }
}
