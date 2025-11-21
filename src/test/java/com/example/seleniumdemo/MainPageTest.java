package com.example.seleniumdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;


import java.time.Duration;
import java.util.List;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        closeCookiesIfPresent();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private void closeCookiesIfPresent() {
        // fecha o banner de cookies se existir – genérico:
        try {
            List<WebElement> cookieButtons =
                    driver.findElements(By.cssSelector("div.ch2-container button"));

            if (!cookieButtons.isEmpty()) {
                cookieButtons.get(0).click();
            }
        } catch (Exception e) {
            // se não houver banner, ignoramos
        }
    }

    @Test
    public void search() {
        // 1) Clica na lupa (abre a barra de pesquisa no header)
        mainPage.searchButton.click();

        // Barra de pesquisa NA PRIMEIRA PÁGINA (antes de pesquisar)
        String initialSearchSelector =
                "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > " +
                        "div.wt-col-inline._siteHeader__searchWrapper_1piyk9v_1 > div > div > div > div > " +
                        "label > div > div > input";

        // 2) Espera que a barra de pesquisa inicial fique visível
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(initialSearchSelector)
                )
        );

        // 3) Escreve "Selenium" na barra de pesquisa da PRIMEIRA página
        searchField.click();
        searchField.sendKeys("Selenium");

        // 4) Clica no botão "Search" da popup
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-test='full-search-button']")
                )
        );
        submitButton.click();

        // 5) Espera pela nova página – URL contém o parâmetro q=Selenium
        wait.until(ExpectedConditions.urlContains("q=Selenium"));
        assertTrue(driver.getCurrentUrl().contains("q=Selenium"));

        // Barra de pesquisa NA SEGUNDA PÁGINA (depois de pesquisar)
        String resultSearchSelector =
                "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > " +
                        "div.wt-col-inline._siteHeader__searchWrapper_1piyk9v_1 > div > div > div._header_1jgvhbp_49 > " +
                        "div > div._input_a454va_7 > div > label > div > div > input";

        // 6) Vai buscar a barra de pesquisa da SEGUNDA página
        WebElement searchPageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(resultSearchSelector)
                )
        );

        // 7) Garante que o valor na barra de pesquisa é "Selenium"
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }






    @Test
    public void toolsMenu() {
        // 1) Espera que o botão "Developer Tools" do header esteja clicável
        WebElement devToolsButton = wait.until(
                ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)
        );

        // 2) Clica para abrir o submenu
        devToolsButton.click();

        // 3) Selector da caixa branca do submenu (o que copiaste do DevTools)
        String toolsSubmenuSelector =
                "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > " +
                        "div.wt-col-auto-fill.wt-col_align-self_stretch._siteHeader__contentPart_1piyk9v_1._siteHeader__desktopContentPart_1piyk9v_1 > div > nav > " +
                        "div:nth-child(2) > div > div > div > div._mainSubmenu__content_13jxbg3_1 > div._mainSubmenu__columnsWrapper_13jxbg3_1";

        // 4) Espera que o submenu (caixa branca) fique visível
        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(toolsSubmenuSelector)
                )
        );

        // 5) Verifica que o submenu está mesmo aberto
        assertTrue(menuPopup.isDisplayed());
    }


    @Test
    public void navigationToAllTools() {
        // 1) Clica no botão grande "See Developer Tools"
        WebElement seeDevTools = wait.until(
                ExpectedConditions.elementToBeClickable(mainPage.seeDeveloperToolsButton)
        );
        seeDevTools.click();

        // 2) Clica no link "Find your tool" (já com o locator novo no MainPage)
        WebElement findYourTools = wait.until(
                ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton)
        );
        findYourTools.click();

        // 3) Espera que a página de produtos carregue (div com id="products-page")
        WebElement productsList = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("products-page")
                )
        );

        // 4) Verifica que a lista de produtos está visível
        assertTrue(productsList.isDisplayed());

        // 5) Verifica o título da página
        assertEquals(
                "All Developer Tools and Products by JetBrains",
                driver.getTitle()
        );
    }



}
