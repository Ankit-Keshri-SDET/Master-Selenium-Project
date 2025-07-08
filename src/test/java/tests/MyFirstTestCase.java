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
        driver.get("https://askomdch.com");
        HomePage hp = new HomePage(driver);
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
        driver.get("https://askomdch.com");
        HomePage hp = new HomePage(driver);
        StorePage sp = hp.clickStoreMenuLink(); // Fluent Interface
        sp.searchProduct("Blue"); // Functional Page Object Example, One method holding multiple user action methods
        Thread.sleep(1500);
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton("Blue Shoes");
        Thread.sleep(500);
        CartPage cp = sp.clickOnViewCartLink();
        Assert.assertEquals(cp.getProductNameOnCartPage(), "Blue Shoes", "Incorrect product added to Cart ...");
        CheckoutPage ccp = cp.clickOnCheckoutBtn();
        // Login With existing account
        ccp.openLoginModal();
        Thread.sleep(2000);
        ccp.enterUserName("smokeTest").
                enterPassword("Pass12345").
                doLogin();
        Thread.sleep(2000);
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
}
