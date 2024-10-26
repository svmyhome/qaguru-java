package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SubmitPracticeFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1000x1900";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    void submitStudentRegistrationFormTest() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivanov@ivan.ru";
        String userNumber = "79536787656";
        String subjects = "Maths";
        String address = "СПБ, невский проспект 16";
        String gender = "Male";
        String sport = "Sports";
        String music = "Music";
        String state = "Uttar Pradesh";
        String city = "Lucknow";
        String date = "07";


        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").type(lastName);

        $("#userEmail").setValue(email);

        $$("label[for^='gender-radio-']").filterBy(text(gender)).first().click();

        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
//        $(".react-datepicker__month-select").selectOptionByValue("10");
//        $(".react-datepicker__year-select").selectOptionByValue("1996");
//        $(".react-datepicker__day--007").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--0" + date + ":not(.react-datepicker__day--outside-month)").click();


        $("#subjectsInput").setValue("h");
        $$("div[id^='react-select-2-option-']").filterBy(text(subjects)).first().click();

        ElementsCollection hobbies = $$("label[for^='hobbies-checkbox']");
        hobbies.filterBy(text(sport)).first().click();
        hobbies.filterBy(text(music)).first().click();


        $("#uploadPicture").uploadFromClasspath("test.jpg");
        $("#currentAddress").setValue(address);

        $("#state").click();
        $$("div[id^=react-select-3-option-").filterBy(text(state)).first().click();

        $("#city").click();
        $$("div[id^=react-select-4-option-").filterBy(text(city)).first().click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        ElementsCollection resultTable = $$(".table-responsive tbody tr");
        resultTable.shouldHave(texts(firstName + " " + lastName, email, gender, "Mobile 7953678765",
                "07 November,1996", subjects, sport + ", " + music, "Picture test.jpg", address, state + " " + city));

    // OR
//        ElementsCollection resultTable = $$(".table-responsive tbody tr");
//        resultTable.filterBy(text("Student Name")).first().shouldHave(text(firstName + " " + lastName));
//        resultTable.filterBy(text("Student Email")).first().shouldHave(text(email));
//        resultTable.filterBy(text("Gender")).first().shouldHave(text(gender));
//        resultTable.filterBy(text("Mobile")).first().shouldHave(text("Mobile 7953678765"));
//        resultTable.filterBy(text("Date of Birth")).first().shouldHave(text("07 November,1996"));
//        resultTable.filterBy(text("Subjects")).first().shouldHave(text(subjects));
//        resultTable.filterBy(text("Hobbies")).first().shouldHave(text(sport + ", " + music));
//        resultTable.filterBy(text("Picture")).first().shouldHave(text("Picture test.jpg"));
//        resultTable.filterBy(text("Address")).first().shouldHave(text(address));
//        resultTable.filterBy(text("State and City")).first().shouldHave(text(state + " " + city));

        // OR
//        $(".table-responsive").shouldHave(text(firstName + " " + lastName), text(email), text(gender),
//                text("Mobile 7953678765"), text("07 November,1996"), text(subjects), text(sport + ", " + music),
//                text("Picture test.jpg"), text(state + " " + city));

    }

}
