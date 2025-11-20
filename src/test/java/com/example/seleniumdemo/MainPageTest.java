package com.example.seleniumdemo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // espera implícita para encontrar elementos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.jetbrains.com/");

        // inicializar Page Object
        mainPage = new MainPage(driver);

        // pequena pausa para garantir que o banner de cookies aparece
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // fechar o banner de cookies, se estiver presente
        mainPage.acceptCookiesIfPresent();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {

        // clicar no ícone de pesquisa
        mainPage.searchButton.click();

        // pequena espera para deixar o campo de pesquisa aparecer
        try { Thread.sleep(1000); } catch (Exception ignored) {}

        // 1) campo de pesquisa no header da página inicial
        WebElement searchField = driver.findElement(By.cssSelector(
                "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > div.wt-col-inline._siteHeader__searchWrapper_1piyk9v_1 > div > div > div > div > label > div > div > input"
        ));
        searchField.sendKeys("Selenium");

        // 2) clicar no botão para fazer a pesquisa
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        // esperar pela página de resultados
        try { Thread.sleep(1500); } catch (Exception ignored) {}

        // 3) campo de pesquisa na PÁGINA DE RESULTADOS
        WebElement searchPageField = driver.findElement(By.cssSelector(
                "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > div.wt-col-inline._siteHeader__searchWrapper_1piyk9v_1 > div > div > div._header_1jgvhbp_49 > div._container_1jgvhbp_38 > div._input_a454va_7 > div > label > div > div > input"
        ));

        // 4) validar que o texto é "Selenium"
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {

        // clicar no botão "Developer Tools" no menu
        mainPage.toolsMenu.click();

        // pequena pausa para o submenu ter tempo de aparecer
        try { Thread.sleep(1000); } catch (Exception ignored) {}

        // localizar o submenu (caixa branca) com o seletor que copiaste
        WebElement menuPopup = driver.findElement(By.cssSelector(
                "#wt-site-header > div > div > div.wt-row.wt-row_align-items_center.wt-row_size_0.wt-row_nowrap._siteHeader__row_1piyk9v_1._siteHeader__rowAdaptive_1piyk9v_1 > div.wt-col-auto-fill.wt-col_align-self_stretch._siteHeader__contentPart_1piyk9v_1._siteHeader__desktopContentPart_1piyk9v_1 > div > nav > div:nth-child(2) > div > div > div > div._mainSubmenu__content_13jxbg3_1 > div._mainSubmenu__columnsWrapper_13jxbg3_1 > div.main-submenu__column._mainSubmenu__column_13jxbg3_1._mainSubmenu__column--auto-fill_13jxbg3_1"
        ));

        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        // clicar em "See Developer Tools"
        mainPage.seeDeveloperToolsButton.click();

        // clicar em "Find your tools"
        mainPage.findYourToolsButton.click();

        // verificar se a lista de produtos aparece
        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());

        // verificar título da página
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
