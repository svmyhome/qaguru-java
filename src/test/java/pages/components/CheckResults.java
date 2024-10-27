package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class CheckResults {

    public static final ElementsCollection tablePracticeForm = $$(".table-responsive tbody tr"),
            tableTextBox = $$("#output p");

    public void checkRow(String key, String value) {
        tablePracticeForm.filterBy(text(key)).first().shouldHave(text(value));
    }

    public void checkElementColor(SelenideElement element, String cssElement, String color) {
        element.shouldHave((cssValue(cssElement, color)));
    }

    public void checkTextBoxResults(String name, String email, String current_address, String permanent_address) {
        tableTextBox.shouldHave(texts(name, email, current_address, permanent_address));
    }
}
