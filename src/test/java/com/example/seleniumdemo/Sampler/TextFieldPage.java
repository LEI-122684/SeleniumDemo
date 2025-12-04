package com.example.seleniumdemo.Sampler;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextFieldPage {

    private static final String URL = "https://demo.vaadin.com/sampler/#ui/data-input/text-input/text-field";

    private final SelenideElement textField = $("input[aria-labelledby='gwt-uid-8']");

    public TextFieldPage openPage() {
        open(URL);
        textField.shouldBe(visible);
        return this;
    }

    public void assertFieldVisible() {
        textField.shouldBe(visible);
    }

    public TextFieldPage typeName(String name) {
        textField.shouldBe(visible).setValue(name);
        return this;
    }

    public void assertFieldValue(String expectedValue) {
        textField.shouldHave(value(expectedValue));
    }
}