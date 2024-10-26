package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class CheckResults {

    public void checkTableResult(String firstName, String lastName, String email, String gender,
                                 String subjects, String sport, String music, String address,
                                 String state, String city) {
        $$(".table-responsive tbody tr").filterBy(text("Student Name")).first().shouldHave(text(firstName + " " + lastName));
        $$(".table-responsive tbody tr").filterBy(text("Student Email")).first().shouldHave(text(email));
        $$(".table-responsive tbody tr").filterBy(text("Gender")).first().shouldHave(text(gender));
        $$(".table-responsive tbody tr").filterBy(text("Mobile")).first().shouldHave(text("Mobile 7953678765"));
        $$(".table-responsive tbody tr").filterBy(text("Date of Birth")).first().shouldHave(text("07 November,1996"));
        $$(".table-responsive tbody tr").filterBy(text("Subjects")).first().shouldHave(text(subjects));
        $$(".table-responsive tbody tr").filterBy(text("Hobbies")).first().shouldHave(text(sport + ", " + music));
        $$(".table-responsive tbody tr").filterBy(text("Picture")).first().shouldHave(text("Picture test.jpg"));
        $$(".table-responsive tbody tr").filterBy(text("Address")).first().shouldHave(text(address));
        $$(".table-responsive tbody tr").filterBy(text("State and City")).first().shouldHave(text(state + " " + city));

    }

    public void checkRequiredFielsTableResult(String firstName, String lastName, String gender) {
        $$(".table-responsive tbody tr").filterBy(text("Student Name")).first().shouldHave(text(firstName + " " + lastName));
        $$(".table-responsive tbody tr").filterBy(text("Gender")).first().shouldHave(text(gender));
        $$(".table-responsive tbody tr").filterBy(text("Mobile")).first().shouldHave(text("Mobile 7953678765"));
        $$(".table-responsive tbody tr").filterBy(text("Date of Birth")).first().shouldHave(text("07 November,1996"));
    }
    
    public void checkElementColor(SelenideElement element, String cssElement, String color) {
        element.shouldHave((cssValue(cssElement, color)));
    }
}
