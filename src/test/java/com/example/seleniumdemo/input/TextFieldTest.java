package com.example.seleniumdemo.input;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

public class TextFieldTest {

    @Test
    void campoDeTexto_deveEstarVisivel() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        new TextFieldPage()
                .openPage()
                .assertFieldVisible();
    }

    @Test
    void campoDeTexto_deveAceitarInputDoUsuario() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        String testName = "Selenide";

        new TextFieldPage()
                .openPage()
                .typeName(testName)
                .assertFieldValue(testName);
    }
}