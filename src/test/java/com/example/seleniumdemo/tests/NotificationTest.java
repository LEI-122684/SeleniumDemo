package com.example.seleniumdemo.tests;

import com.example.seleniumdemo.pages.NotificationPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationTest {

    private WebDriver driver;
    private NotificationPage notificationPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        notificationPage = new NotificationPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void notificationAppears() {
        notificationPage.clickHereButton.click();

        String text = notificationPage.message.getText();

        assertTrue(text.contains("Action"),
                "A mensagem não apareceu corretamente.");
    }
}
