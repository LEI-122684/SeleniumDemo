package com.example.seleniumdemo.database.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DatabasePage {

    private static final String URL = "https://vaadin-database-example.demo.vaadin.com/";

    // grid completo
    private final SelenideElement grid = $("vaadin-grid");

    // todas as células de texto da tabela
    private final ElementsCollection cells = grid.$$("vaadin-grid-cell-content");

    // links "Click to IMBD site"
    private final ElementsCollection imdbLinks = grid.$$("[href]");

    public DatabasePage openPage() {
        open(URL);
        grid.shouldBe(visible);
        return this;
    }

    /** Verifica que todos os filmes e info aparecem na tabela */
    public DatabasePage assertMovieInfoVisible() {
        cells.findBy(com.codeborne.selenide.Condition.text("Law Abiding Citizen")).shouldBe(visible);
        cells.findBy(com.codeborne.selenide.Condition.text("Knives Out")).shouldBe(visible);
        cells.findBy(com.codeborne.selenide.Condition.text("The Last Jedi")).shouldBe(visible);

        cells.findBy(com.codeborne.selenide.Condition.text("2009")).shouldBe(visible);
        cells.findBy(com.codeborne.selenide.Condition.text("2019")).shouldBe(visible);
        cells.findBy(com.codeborne.selenide.Condition.text("2017")).shouldBe(visible);

        cells.findBy(com.codeborne.selenide.Condition.text("F. Gardy Gray")).shouldBe(visible);
        cells.findBy(com.codeborne.selenide.Condition.text("Rian Johnson")).shouldBe(visible);

        return this;
    }

    /** Clica no primeiro link IMBD da tabela */
    public DatabasePage clickFirstImdbLink() {
        imdbLinks.first().shouldBe(visible).click();
        return this;
    }
}
