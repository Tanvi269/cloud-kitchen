package com.cloud.kitchen;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CloudKitchenSeleniumTest {

    @Test
    void testCloudKitchenPage() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081/cloud-kitchen/");
            driver.manage().window().maximize();

            String title = driver.getTitle();

            assertTrue(title != null && !title.isEmpty());

            System.out.println("Cloud Kitchen page opened successfully.");
            System.out.println("Page Title: " + title);

        } finally {
            driver.quit();
        }
    }
}