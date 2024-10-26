package com.demoqa;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TextBoxPageTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillAllFieldTest() {
        textBoxPage.openPage()
                .fillFullName("Иванов Иван")
                .fillEmail("afdsdas@mail.ru")
                .fillCurrentAddress("Spb nevsky 19")
                .fillPermanentAddress("Spb nevsky 19")
                .clickSubmit();

        $$("#output").shouldHave(texts("Иванов Иван", "afdsdas@mail.ru", "Spb nevsky 19", "Spb nevsky 19"));
    }
}
