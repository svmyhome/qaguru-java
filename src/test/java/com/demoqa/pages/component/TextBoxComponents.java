package com.demoqa.pages.component;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$;

public class TextBoxComponents {
    private static final ElementsCollection formTextBox = $$("#output");

    public void checkTextBoxResults(String value) {
        formTextBox.shouldHave(texts(value));
    }
}
