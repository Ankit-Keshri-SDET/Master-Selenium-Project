package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//td[@data-title='Product']//a")
    private WebElement productName;
    @FindBy(css = ".checkout-button")
    private WebElement checkOutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameOnCartPage(){
        return productName.getText();
    }

    public CheckoutPage clickOnCheckoutBtn(){
        checkOutBtn.click();
        return new CheckoutPage(driver);
    }
}
