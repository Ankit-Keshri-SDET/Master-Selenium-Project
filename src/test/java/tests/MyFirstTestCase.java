package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HomePage;
import pages.StorePage;

import java.time.Duration;

public class MyFirstTestCase extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com");

        HomePage hp = new HomePage(driver);
        StorePage sp = hp.clickStoreMenuLink(); // Fluent Interface
        sp.enterValueInSearchField("Blue").clickSearchButton(); // Builder Pattern
        Thread.sleep(1500);
        Assert.assertTrue(sp.getTitle().contains("Blue"), "Incorrect Search Results ...");
        sp.clickAddToCartButton();
        Thread.sleep(500);
        sp.clickOnViewCartLink();

//        WebElement productName = driver.findElement(By.xpath("//td[@data-title='Product']//a"));
//        Assert.assertEquals(productName.getText(), "Blue Shoes", "Incorrect product added to Cart ...");
//        driver.findElement(By.cssSelector(".checkout-button")).click();
//        driver.findElement(By.id("billing_first_name")).sendKeys("Tester");
//        driver.findElement(By.id("billing_last_name")).sendKeys("Smoke");
//        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
//        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
//        driver.findElement(By.id("billing_postcode")).sendKeys("87178");
//        driver.findElement(By.id("billing_email")).sendKeys("smokeT@gmail.com");
//        driver.findElement(By.id("place_order")).click();
//        Thread.sleep(2000);
//        Assert.assertEquals(driver.findElement(
//                        By.cssSelector(".woocommerce-notice")).getText(),
//                "Thank you. Your order has been received.");
        driver.quit();
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://askomdch.com");
        driver.findElement(By.linkText("Store")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        Thread.sleep(1500);
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Blue"));
        driver.findElement(By.cssSelector("a[aria-label*='Blue Shoes']")).click();
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Thread.sleep(500);
        WebElement productName = driver.findElement(By.xpath("//td[@data-title='Product']//a"));
        Assert.assertEquals(productName.getText(), "Blue Shoes", "Incorrect product added to Cart ...");
        driver.findElement(By.cssSelector(".checkout-button")).click();
        // Login With existing account
        driver.findElement(By.xpath("//a[@class='showlogin']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("smokeTest");
        driver.findElement(By.id("password")).sendKeys("Pass12345");
        driver.findElement(By.cssSelector("button[name='login']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("billing_first_name")).sendKeys("Tester");
        driver.findElement(By.id("billing_last_name")).sendKeys("Smoke");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("87178");
        // Clear user state from before
        driver.findElement(By.id("billing_email")).clear();
        // We are depending on user state which is not a good approach
        driver.findElement(By.id("billing_email")).sendKeys("smokeT@gmail.com");
        Thread.sleep(500);
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(1000);
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(
                        By.cssSelector(".woocommerce-notice")).getText(),
                "Thank you. Your order has been received.");
        driver.quit();
    }
}
