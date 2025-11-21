package com.example.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://the-internet.herokuapp.com/inputs
public class InputsPage {

    private WebDriver driver;

    // Campo de input numérico (ou genérico, caso o type mude)
    @FindBy(css = "input[type='number'], input")
    public WebElement numberInput;

    public InputsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
