package pages;

import base.BasePage;
import objects.BillingAddress;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterBillingFirstName(String firstName) {
        billing_firstName.clear();
        billing_firstName.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterBillingLastName(String lastName) {
        billing_lastName.clear();
        billing_lastName.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        billing_addressLine.clear();
        billing_addressLine.sendKeys(address);
        return this;
    }

    public CheckoutPage selectState(String state) {
        Select select = new Select(billingState);
        select.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage enterBillingCity(String city) {
        billing_city.clear();
        billing_city.sendKeys(city);
        return this;
    }

    public CheckoutPage enterBillingZip(String zip) {
        postCode.clear();
        postCode.sendKeys(zip);
        return this;
    }

    public CheckoutPage enterBillingEmail(String email) {
        emailID.clear();
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
        showLoginLink.click();
        return this;
    }

    public CheckoutPage enterUserName(String usn) {
        userNameField.sendKeys(usn);
        return this;
    }

    public CheckoutPage enterPassword(String pass) {
        passwordField.sendKeys(pass);
        return this;
    }

    public CheckoutPage doLogin() {
        clickLoginBtn.click();
        return this;
    }

    public CheckoutPage login(String usn, String pass) {
        openLoginModal();
        enterUserName(usn);
        enterPassword(pass);
        doLogin();
        return this;
    }

    public CheckoutPage enterBillingDetails(BillingAddress billingAddress) {
        return (enterBillingFirstName(billingAddress.getBillingFirstName()).
                enterBillingLastName(billingAddress.getBillingLastName()).
                enterBillingCity(billingAddress.getBillingAddress()).
                enterBillingCity(billingAddress.getBillingCity()).
                enterBillingZip(billingAddress.getBillingZipCode()).
                enterBillingEmail(billingAddress.getBillingEmail()));
    }

    public CheckoutPage placeOrder() {
        placeOrderButton.click();
        return this;
    }

    public String getNotice() {
        return noticeTxt.getText();
    }
}
