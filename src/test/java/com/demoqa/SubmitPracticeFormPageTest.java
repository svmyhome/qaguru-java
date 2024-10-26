package com.demoqa;

import org.junit.jupiter.api.Test;

import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SubmitPracticeFormPageTest extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void submitStudentRegistrationFormFillAllFieldsTest() {
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
        String photo = "test.jpg";

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth("10", "1996", "07")
                .setSubjects("h", subjects)
                .selectHobbies(sport, music)
                .uploadPicture(photo)
                .currentAddress(address)
                .setState(state)
                .selectCity(city)
                .submitButton();
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        registrationPage.checkResultAllFields(firstName, lastName, email, gender,
                subjects, sport, music, address, state, city);
    }

    @Test
    void submitStudentRegistrationFormFillRequiredFieldsTest() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String userNumber = "79536787656";
        String gender = "Male";


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth("10", "1996", "07")
                .submitButton();
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        registrationPage.checkResultRequiredFields(firstName, lastName, gender);
    }

    @Test
    void submitStudentRegistrationFormFillRequiredFieldsNegativeTest() {
        registrationPage.openPage()
                .submitButton()
                .checkFirsNameInputColor("border-color", "rgb(220, 53, 69)")
                .checkLastNameInputColor("border-color", "rgb(220, 53, 69)")
                .checkGenderColor("border-color", "rgb(220, 53, 69)")
                .checkPhoneInputColor("border-color", "rgb(220, 53, 69)")
                .checkSubmitFormNotVisible();
    }

}
