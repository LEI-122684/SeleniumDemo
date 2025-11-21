package com.example.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DropdownPage {

    @FindBy(id = "dropdown")
    public WebElement dropdown;

    public DropdownPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
