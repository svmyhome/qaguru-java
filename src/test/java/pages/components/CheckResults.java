package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class CheckResults {

    public static final ElementsCollection tablePacticeForm = $$(".table-responsive tbody tr"),
            tableTextBox = $$("#output p");

    public void checkTableResult(String firstName, String lastName, String email, String gender, String dateOfBirth,
                                 String subjects, String sport, String music, String address,
                                 String state, String city) {
        tablePacticeForm.filterBy(text("Student Name")).first().shouldHave(text(firstName + " " + lastName));
        tablePacticeForm.filterBy(text("Student Email")).first().shouldHave(text(email));
        tablePacticeForm.filterBy(text("Gender")).first().shouldHave(text(gender));
        tablePacticeForm.filterBy(text("Mobile")).first().shouldHave(text("Mobile 7953678765"));
        tablePacticeForm.filterBy(text("Date of Birth")).first().shouldHave(text(dateOfBirth));
        tablePacticeForm.filterBy(text("Subjects")).first().shouldHave(text(subjects));
        tablePacticeForm.filterBy(text("Hobbies")).first().shouldHave(text(sport + ", " + music));
        tablePacticeForm.filterBy(text("Picture")).first().shouldHave(text("Picture test.jpg"));
        tablePacticeForm.filterBy(text("Address")).first().shouldHave(text(address));
        tablePacticeForm.filterBy(text("State and City")).first().shouldHave(text(state + " " + city));
    }

    public void checkRequiredFieldsTableResult(String firstName, String lastName, String gender, String dateOfBirth) {
        tablePacticeForm.filterBy(text("Student Name")).first().shouldHave(text(firstName + " " + lastName));
        tablePacticeForm.filterBy(text("Gender")).first().shouldHave(text(gender));
        tablePacticeForm.filterBy(text("Mobile")).first().shouldHave(text("Mobile 7953678765"));
        tablePacticeForm.filterBy(text("Date of Birth")).first().shouldHave(text(dateOfBirth));
    }

    public void checkElementColor(SelenideElement element, String cssElement, String color) {
        element.shouldHave((cssValue(cssElement, color)));
    }

    public void checkTextBoxResults(String name, String email, String current_address, String permanent_address) {
        tableTextBox.shouldHave(texts(name, email, current_address, permanent_address));
    }
}
