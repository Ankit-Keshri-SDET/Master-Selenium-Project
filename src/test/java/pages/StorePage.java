package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorePage extends BasePage {

    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;
    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement searchButton;
    @FindBy(tagName = "h1")
    private WebElement title;
    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage enterValueInSearchField(String val) {
        wait.until(ExpectedConditions.visibilityOf(searchField)).sendKeys(val);
        return this;
    }

    public StorePage clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        return this;
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOf(title)).getText();
    }

    private By getAddToCartBtnElement(String productName) {
        return By.cssSelector("a[aria-label*='" + productName + "']");
    }

    public StorePage clickAddToCartButton(String productName) {
        By addToCartButton = getAddToCartBtnElement(productName);
        driver.findElement(addToCartButton).click();
        return this;
    }

    public StorePage searchProduct(String value) {
        enterValueInSearchField(value);
        clickSearchButton();
        return this;
    }

    public CartPage clickOnViewCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
