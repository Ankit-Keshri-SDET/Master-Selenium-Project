package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(xpath = "//td[@data-title='Product']//a")
    private WebElement productName;
    @FindBy(css = ".checkout-button")
    private WebElement checkOutBtn;
    @FindBy(tagName = "h1")
    private WebElement cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.textToBePresentInElement(cartHeading, "Cart"));
    }

    public String getProductNameOnCartPage() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage clickOnCheckoutBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
        return new CheckoutPage(driver);
    }
}
