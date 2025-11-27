package com.example.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotificationPage {

    @FindBy(css = "a[href='/notification_message']")
    public WebElement clickHereButton;

    @FindBy(id = "flash")
    public WebElement message;

    public NotificationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
