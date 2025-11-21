package com.example.seleniumdemo.tests;

import com.example.seleniumdemo.pages.InputsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class InputsTest {

    private WebDriver driver;
    private InputsPage inputsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/inputs");
        inputsPage = new InputsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void typeNumberInInput() {
        // Limpa o campo e escreve 123
        inputsPage.numberInput.clear();
        inputsPage.numberInput.sendKeys("123");

        // Valida o valor escrito
        String value = inputsPage.numberInput.getAttribute("value");
        assertEquals("123", value);
    }
}
