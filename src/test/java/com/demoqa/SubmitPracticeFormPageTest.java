package com.demoqa;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class SubmitPracticeFormPageTest extends TestBase {

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
    String date = "07";
    String month = "November";
    String year = "1996";
    String dateMonthYear = String.format("%s %s,%s", date, month, year); //date + " " + month+;

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void submitStudentRegistrationFormFillAllFieldsTest() {


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth(month, year, date)
                .setSubjects("h", subjects)
                .selectHobbies(sport, music)
                .uploadPicture(photo)
                .currentAddress(address)
                .setState(state)
                .selectCity(city)
                .submitButton();
        registrationPage.checkResultAllFields(firstName, lastName, email, gender, dateMonthYear,
                subjects, sport, music, address, state, city);
    }

    @Test
    void submitStudentRegistrationFormFillRequiredFieldsTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth(month, year, date)
                .submitButton();
        registrationPage.checkResultRequiredFields(firstName, lastName, gender, dateMonthYear);
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
