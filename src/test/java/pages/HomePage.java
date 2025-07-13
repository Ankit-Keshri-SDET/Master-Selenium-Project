package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(linkText = "Store")
    private WebElement storeLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage loadURL() {
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public StorePage clickStoreMenuLink() {
        wait.until(ExpectedConditions.elementToBeClickable(storeLink)).click();
        return new StorePage(driver);
    }
}
