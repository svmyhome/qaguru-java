package jenkins;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SubmitPracticeFormRemoteTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1000x1900";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    @Tag("DEMOQA")
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

        step("Открыта главная страница", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        step("Заполнено фио", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").type(lastName);
        });

        step("Заполнено почту", () -> {
            $("#userEmail").setValue(email);
        });
        step("Заполнен пол", () -> {
            $$("label[for^='gender-radio-']").filterBy(text(gender)).first().click();
        });
        step("Заполнено номер телефона", () -> {
            $("#userNumber").setValue(userNumber);
        });
        step("Заполнена дата", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("November");
            $(".react-datepicker__year-select").selectOption("1996");
            $(".react-datepicker__day--0" + date + ":not(.react-datepicker__day--outside-month)").click();
        });

        step("Заполнены предметы", () -> {
            $("#subjectsInput").setValue("h");
            $$("div[id^='react-select-2-option-']").filterBy(text(subjects)).first().click();
        });
        step("Заполнены хобби", () -> {
            ElementsCollection hobbies = $$("label[for^='hobbies-checkbox']");
            hobbies.filterBy(text(sport)).first().click();
            hobbies.filterBy(text(music)).first().click();
        });
        step("Загружена картинка", () -> {
            $("#uploadPicture").uploadFromClasspath("images/test.jpg");
        });
        step("Заполнен адрес", () -> {
            $("#currentAddress").setValue(address);
        });
        step("Заполнен штат", () -> {
            $("#state").click();
            $$("div[id^=react-select-3-option-").filterBy(text(state)).first().click();
        });
        step("Заполнен город", () -> {
            $("#city").click();
            $$("div[id^=react-select-4-option-").filterBy(text(city)).first().click();
            $("#submit").click();
        });

        step("Все поля заполнены", () -> {
            $(".modal-title").shouldHave(text("Thanks for submitting the form"));


            ElementsCollection resultTable = $$(".table-responsive tbody tr");
            resultTable.shouldHave(texts(firstName + " " + lastName, email, gender, "Mobile 7953678765",
                    "07 November,1996", subjects, sport + ", " + music, "Picture test.jpg", address, state + " " + city));
        });
    }

}
