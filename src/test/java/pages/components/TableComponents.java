package pages.components;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TableComponents {

    private static final ElementsCollection tablePracticeForm = $$(".table-responsive tbody tr");

    public void checkRow(String key, String value) {
        tablePracticeForm.filterBy(text(key)).first().shouldHave(text(value));
    }

}
