package com.demoqa.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class CheckResults {

    private static final ElementsCollection tablePracticeForm = $$(".table-responsive tbody tr"),
            formTextBox = $$("#output");

    public void checkRow(String key, String value) {
        tablePracticeForm.filterBy(text(key)).first().shouldHave(text(value));
    }

    public void checkElementColor(SelenideElement element, String cssElement, String color) {
        element.shouldHave((cssValue(cssElement, color)));
    }

    public void checkTextBoxResults(String value) {
        formTextBox.shouldHave(texts(value));
    }
}
