package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.StorePage;

import java.time.Duration;

public class MyFirstTestCase extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        HomePage hp = new HomePage(driver).loadURL();
        StorePage sp = hp.clickStoreMenuLink(); // Fluent Interface
        sp.searchProduct("Blue"); // Functional Page Object Example, One method holding multiple user action methods
        Thread.sleep(1500);
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton("Blue Shoes");
        Thread.sleep(500);
        CartPage cp = sp.clickOnViewCartLink();
        Assert.assertEquals(cp.getProductNameOnCartPage(), "Blue Shoes", "Incorrect product added to Cart ...");
        CheckoutPage ccp = cp.clickOnCheckoutBtn();
        ccp.enterBillingFirstName("Tester")
                .enterBillingLastName("Smoke")
                .enterAddress("San Francisco")
                .enterBillingCity("San Francisco")
                .enterBillingZip("87187")
                .enterBillingEmail("smokeT@gmail.com")
                .placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(ccp.getNotice(), "Thank you. Your order has been received.");
        driver.quit();
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
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
        CheckoutPage ccp = cp.clickOnCheckoutBtn();

        ccp.login("smokeTest", "Pass12345")
                .enterBillingFirstName("Tester")
                .enterBillingLastName("Smoke")
                .enterAddress("San Francisco")
                .enterBillingCity("San Francisco")
                .enterBillingZip("87187")
                .enterBillingEmail("smokeT@gmail.com")
                .placeOrder();
        Thread.sleep(2000);

        Assert.assertEquals(ccp.getNotice(), "Thank you. Your order has been received.");
        driver.quit();
    }
}
