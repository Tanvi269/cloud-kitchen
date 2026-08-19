package com.cloud.kitchen;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CloudKitchenSeleniumTest {

    @Test
    void testAddToCart() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081/cloud-kitchen/");
            driver.manage().window().maximize();

            // Click Chicken Biryani Add to Cart button
            driver.findElement(
                By.xpath("//button[contains(@onclick,\"Chicken Biryani\")]")
            ).click();

            // Close the JavaScript alert
            driver.switchTo().alert().accept();

            // Check cart text
            String cartText = driver.findElement(By.id("cart")).getText();

            System.out.println("Cart after adding item: " + cartText);

            assertTrue(cartText.contains("1"));

        } finally {
            driver.quit();
        }
    }
}