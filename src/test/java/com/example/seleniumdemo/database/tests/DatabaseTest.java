package com.example.seleniumdemo.database.tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.example.seleniumdemo.database.pages.DatabasePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.switchTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {

    @Test
    void informacaoSobreFilmes_deveEstarVisivelNaTabela() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

        new DatabasePage()
                .openPage()
                .assertMovieInfoVisible();
    }

    @Test
    void linkImdbDoPrimeiroFilme_deveAbrirSiteImdb() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

        DatabasePage page = new DatabasePage()
                .openPage();

        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();

        page.clickFirstImdbLink();

        // muda para o novo separador
        switchTo().window(1);

        String url = WebDriverRunner.url();
        assertTrue(url.toLowerCase().contains("imdb"));

        // (opcional) voltar à janela original
        switchTo().window(originalWindow);
    }

}
