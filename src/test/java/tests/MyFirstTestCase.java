package tests;

import objects.BillingAddress;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.StorePage;

public class MyFirstTestCase extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        // Domain Object
//        BillingAddress billingAddress = new BillingAddress()
//                .setBillingFirstName("Tester")
//                .setBillingLastName("Smoke")
//                .setBillingAddress("San Francisco")
//                .setBillingCity("San Francisco")
//                .setBillingZipCode("87161")
//                .setBillingEmail("smokeT@gmail.com");

        BillingAddress billingAddress = new BillingAddress("Tester", "Smoke",
                "San Francisco", "San Francisco", "791901", "smokeT@gmail.com");

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
                .enterBillingDetails(billingAddress) // Call method of Domain Object
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
