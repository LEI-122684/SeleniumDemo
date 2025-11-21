package com.example.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://the-internet.herokuapp.com/checkboxes
public class CheckboxesPage {

    private WebDriver driver;

    // Primeiro checkbox
    @FindBy(css = "#checkboxes input:nth-of-type(1)")
    public WebElement firstCheckbox;

    // Segundo checkbox
    @FindBy(css = "#checkboxes input:nth-of-type(2)")
    public WebElement secondCheckbox;

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
