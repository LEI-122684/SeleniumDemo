package com.example.seleniumdemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(css = "a[data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    @FindBy(css = "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > div.wt-col-auto-fill.wt-col_align-self_stretch._siteHeader__contentPart_1piyk9v_1._siteHeader__desktopContentPart_1piyk9v_1 > div > nav > div:nth-child(2) > button")
    public WebElement toolsMenu;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    @FindBy(css = "#ch2-dialog > div.ch2-dialog-actions.ch2-dialog-actions-vertical > button.ch2-btn.ch2-allow-all-btn.ch2-btn-primary")
    public WebElement acceptCookiesButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void acceptCookiesIfPresent() {
        try {
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (Exception e) {
            // ignorar se não existir
        }
    }
}
