package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "Store")
    private WebElement storeLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage clickStoreMenuLink() {
        storeLink.click();
        return new StorePage(driver);
    }
}
