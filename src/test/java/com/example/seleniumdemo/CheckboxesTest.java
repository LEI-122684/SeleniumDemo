package com.example.seleniumdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class CheckboxesTest {

    private WebDriver driver;
    private CheckboxesPage checkboxesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage = new CheckboxesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void toggleCheckboxes() {
        // Estado inicial
        boolean firstInitiallySelected = checkboxesPage.firstCheckbox.isSelected();
        boolean secondInitiallySelected = checkboxesPage.secondCheckbox.isSelected();

        // Clicar em ambos
        checkboxesPage.firstCheckbox.click();
        checkboxesPage.secondCheckbox.click();

        // Verifica que os estados foram alterados
        assertNotEquals(firstInitiallySelected, checkboxesPage.firstCheckbox.isSelected());
        assertNotEquals(secondInitiallySelected, checkboxesPage.secondCheckbox.isSelected());
    }
}
