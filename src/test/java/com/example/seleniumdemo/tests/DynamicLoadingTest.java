package com.example.seleniumdemo.tests;


import com.example.seleniumdemo.pages.DynamicLoadingPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicLoadingTest {
    private WebDriver driver;
    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        dynamicLoadingPage = new DynamicLoadingPage(driver);
    }

    @Test
    public void testDynamicContentLoad() {
        dynamicLoadingPage.open();

        dynamicLoadingPage.clickStart();

        String actualText = dynamicLoadingPage.getFinishText();

        Assertions.assertEquals("Hello World!", actualText, "O texto apresentado não corresponde ao esperado.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}