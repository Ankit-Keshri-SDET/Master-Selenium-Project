package pages;

import base.BasePage;
import objects.BillingAddress;
import objects.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckoutPage extends BasePage {
    @FindBy(id = "billing_first_name")
    private WebElement billing_firstName;
    @FindBy(id = "billing_last_name")
    private WebElement billing_lastName;
    @FindBy(id = "billing_address_1")
    private WebElement billing_addressLine;
    @FindBy(id = "billing_city")
    private WebElement billing_city;
    @FindBy(id = "billing_state")
    private WebElement billingState;
    @FindBy(id = "billing_postcode")
    private WebElement postCode;
    @FindBy(id = "billing_email")
    private WebElement emailID;
    @FindBy(id = "place_order")
    private WebElement placeOrderButton;
    @FindBy(css = ".woocommerce-notice")
    private WebElement noticeTxt;
    @FindBy(xpath = "//a[@class='showlogin']")
    private WebElement showLoginLink;
    @FindBy(id = "username")
    private WebElement userNameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css = "button[name='login']")
    private WebElement clickLoginBtn;
    // Section 11 - Synchronization - ImplicitWait
    @FindBy(css = ".blockUI.blockOverlay")
    private List<WebElement> overlayElement;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterBillingFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(billing_firstName)).clear();
        billing_firstName.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterBillingLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(billing_lastName)).clear();
        billing_lastName.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        wait.until(ExpectedConditions.visibilityOf(billing_addressLine)).clear();
        billing_addressLine.sendKeys(address);
        return this;
    }

    public CheckoutPage selectState(String state) {
        Select select = new Select(billingState);
        select.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage enterBillingCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(billing_city)).clear();
        billing_city.sendKeys(city);
        return this;
    }

    public CheckoutPage enterBillingZip(String zip) {
        wait.until(ExpectedConditions.visibilityOf(postCode)).clear();
        postCode.sendKeys(zip);
        return this;
    }

    public CheckoutPage enterBillingEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailID)).clear();
        emailID.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingDetails(String firstname, String lastname,
                                          String address, String city,
                                          String state, String zip, String email) {
        return enterBillingFirstName(firstname).
                enterBillingLastName(lastname).
                enterAddress(address).
                enterBillingCity(city).
                selectState(state).
                enterBillingZip(zip).
                enterBillingEmail(email);
    }

    public CheckoutPage openLoginModal() {
        wait.until(ExpectedConditions.elementToBeClickable(showLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String usn) {
        wait.until(ExpectedConditions.visibilityOf(userNameField)).sendKeys(usn);
        return this;
    }

    public CheckoutPage enterPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOf(userNameField)).sendKeys(pass);
        return this;
    }

    public CheckoutPage doLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(clickLoginBtn)).click();
        return this;
    }

    public CheckoutPage login(User user) {
        openLoginModal();
        enterUserName(user.getUsername());
        enterPassword(user.getPassword());
        doLogin();
        return this;
    }

    public CheckoutPage enterBillingDetails(BillingAddress billingAddress) {
        return (enterBillingFirstName(billingAddress.getBillingFirstName()).
                enterBillingLastName(billingAddress.getBillingLastName()).
                enterAddress(billingAddress.getBillingAddress()).
                enterBillingCity(billingAddress.getBillingCity()).
                enterBillingZip(billingAddress.getBillingZipCode()).
                enterBillingEmail(billingAddress.getBillingEmail()));
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDissapear(overlayElement);
        placeOrderButton.click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOf(noticeTxt)).getText();
    }
}
