package tests;

import objects.BillingAddress;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.StorePage;
import utils.JacksonUtils;
import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

//        BillingAddress billingAddress = new BillingAddress("Tester", "Smoke",
//                "San Francisco", "San Francisco", "791901", "smokeT@gmail.com");

        // JacksonBind API
        BillingAddress billingAddress = new BillingAddress();
        InputStream is = getClass().getClassLoader().getResourceAsStream("myBillingAddress.json");
        billingAddress = JacksonUtils.deserializeJson(is, billingAddress);

        HomePage hp = new HomePage(driver).loadURL();
        StorePage sp = hp.clickStoreMenuLink();
        sp.searchProduct("Blue");
        Thread.sleep(1500);
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton("Blue Shoes");
        Thread.sleep(500);
        CartPage cp = sp.clickOnViewCartLink();
        Assert.assertEquals(cp.getProductNameOnCartPage(), "Blue Shoes", "Incorrect product added to Cart ...");
        CheckoutPage ccp = cp.clickOnCheckoutBtn()
                .enterBillingDetails(billingAddress)
                .placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(ccp.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        BillingAddress billingAddress = new BillingAddress()
                .setBillingFirstName("Tester")
                .setBillingLastName("Smoke")
                .setBillingAddress("San Francisco")
                .setBillingCity("San Francisco")
                .setBillingZipCode("87161")
                .setBillingEmail("smokeT@gmail.com");
        StorePage sp = new HomePage(driver)
                .loadURL()
                .clickStoreMenuLink()
                .searchProduct("Blue");
        Thread.sleep(1500);
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton("Blue Shoes");
        Thread.sleep(500);
        CartPage cp = sp.clickOnViewCartLink();
        Assert.assertEquals(cp.getProductNameOnCartPage(), "Blue Shoes", "Incorrect product added to Cart ...");
        CheckoutPage ccp = cp.clickOnCheckoutBtn()
                .enterBillingDetails(billingAddress)
                .placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(ccp.getNotice(), "Thank you. Your order has been received.");
    }
}
