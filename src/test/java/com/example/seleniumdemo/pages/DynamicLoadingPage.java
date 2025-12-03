package com.example.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By startButton = By.cssSelector("#start button");
    private final By loadingBar = By.id("loading");
    private final By finishText = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }

    public String getFinishText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishText));

        return driver.findElement(finishText).getText();
    }

    public void waitForLoadingToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBar));
    }
}