package com.example.seleniumdemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {

    // Botão grande "See Developer Tools" na homepage
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    // Link "Find your tool" que leva à página de produtos
    // (este é o NOVO locator – repára em data-test='suggestion-link')
    @FindBy(css = "a[data-test='suggestion-link'][aria-label='Find your tool']")
    public WebElement findYourToolsButton;

    // Item de menu "Developer Tools" (botão no header que abre o submenu)
    @FindBy(css = "button._mainMenuItem__action_adpfl3_19[data-test='main-menu-item-action'][aria-label='Developer Tools: Open submenu']")
    public WebElement toolsMenu;

    // Botão de pesquisa (ícone da lupa no header)
    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
