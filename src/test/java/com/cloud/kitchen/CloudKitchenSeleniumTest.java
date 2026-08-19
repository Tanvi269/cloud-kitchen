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

            // Add Chicken Biryani
            driver.findElement(
                By.xpath("//button[contains(@onclick,\"Chicken Biryani\")]")
            ).click();

            // Close alert
            driver.switchTo().alert().accept();

            // Check cart
            String cartText = driver.findElement(By.id("cart")).getText();

            System.out.println("Cart after adding item: " + cartText);

            assertTrue(cartText.contains("1"));

        } finally {
            driver.quit();
        }
    }

    @Test
    void testAddMultipleItems() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081/cloud-kitchen/");
            driver.manage().window().maximize();

            // Add Pizza
            driver.findElement(
                By.xpath("//button[contains(@onclick,\"Pizza\")]")
            ).click();

            driver.switchTo().alert().accept();

            // Add Classic Burger
            driver.findElement(
                By.xpath("//button[contains(@onclick,\"Classic Burger\")]")
            ).click();

            driver.switchTo().alert().accept();

            // Check cart
            String cartText = driver.findElement(By.id("cart")).getText();

            System.out.println("Cart after adding two items: " + cartText);

            assertTrue(cartText.contains("2"));

        } finally {
            driver.quit();
        }
    }
}