package tests;

import objects.BillingAddress;
import objects.Products;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.StorePage;
import utils.ConfigLoader;
import utils.JacksonUtils;
import java.io.IOException;

public class MyFirstTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Products product = new Products(1215);
        HomePage hp = new HomePage(getDriver()).loadURL();
        StorePage sp = hp.clickStoreMenuLink();
        sp.isLoaded();
        sp.searchProduct("Blue");
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton(product.getName());
        CartPage cp = sp.clickOnViewCartLink();
        cp.isLoaded();
        Assert.assertEquals(cp.getProductNameOnCartPage(), product.getName(), "Incorrect product added to Cart ...");
        CheckoutPage ccp = cp.clickOnCheckoutBtn()
                .enterBillingDetails(billingAddress)
                .selectDirectBankTransferOption()
                .placeOrder();
        Assert.assertEquals(ccp.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Products product = new Products(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
                             ConfigLoader.getInstance().getPassword());
        StorePage sp = new HomePage(getDriver())
                .loadURL()
                .clickStoreMenuLink()
                .searchProduct("Blue");
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton(product.getName());
        CartPage cp = sp.clickOnViewCartLink();
        cp.isLoaded();
        Assert.assertEquals(cp.getProductNameOnCartPage(), product.getName(), "Incorrect product added to Cart ...");
        CheckoutPage ccp = cp.clickOnCheckoutBtn()
                .login(user)
                .enterBillingDetails(billingAddress)
                .selectDirectBankTransferOption()
                .placeOrder();
        Assert.assertEquals(ccp.getNotice(), "Thank you. Your order has been received.");
    }
}
