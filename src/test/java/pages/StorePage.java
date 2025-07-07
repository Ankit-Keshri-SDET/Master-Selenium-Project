package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StorePage extends BasePage {

    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;
    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement searchButton;
    @FindBy(tagName = "h1")
    private WebElement title;
    @FindBy(css = "a[aria-label*='Blue Shoes']")
    private WebElement addToCartButton;
    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterValueInSearchField(String val) {
        searchField.sendKeys(val);
        return this;
    }

    public StorePage clickSearchButton() {
        searchButton.click();
        return this;
    }

    public String getTitle() {
        return title.getText();
    }

    public StorePage clickAddToCartButton() {
        addToCartButton.click();
        return this;
    }

    public void searchProduct(String value){
        enterValueInSearchField(value);
        clickSearchButton();
    }

    public void clickOnViewCartLink() {
        viewCartLink.click();
    }
}
