package com.HospitalManagementSystem.HospitalManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() throws Exception {
        // Ensure that the ChromeDriver is running on the specified port
        URL driverUrl = new URL("http://129.0.6668.100.0"); // Adjust the port if necessary
        ChromeOptions options = new ChromeOptions();
        // You can add options like this:
        // options.addArguments("--headless"); // Uncomment to run in headless mode
        // options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
        driver = new RemoteWebDriver(driverUrl, options);
    }

    @Test
    public void testPageTitle() {
        driver.get("http://localhost:5173/"); // Replace with your React app's URL
        String title = driver.getTitle();
        assertEquals("Expected Page Title", title); // Replace with the actual expected title
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
