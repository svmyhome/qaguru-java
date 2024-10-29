package com.demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.Locale;

public class SubmitPracticeFormPageTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker fakerRu = new Faker(new Locale("ru"));
    Faker fakerEng = new Faker(new Locale("en-US"));


    String firstName = fakerRu.name().firstName();
    String lastName = fakerRu.name().lastName();
    String email = fakerEng.internet().emailAddress();
    String userNumber = fakerRu.phoneNumber().subscriberNumber(10);
    String charSubject = "m";
    String subjects = "Maths";
    String address = fakerRu.address().streetAddress();
    String gender = "Male";
    String sport = "Sports";
    String music = "Music";
    String state = "Uttar Pradesh";
    String city = "Lucknow";
    String photo = "test.jpg";
    String date = "07";
    String month = "November";
    String year = "1996";
    String dateOfBirth = String.format("%s %s,%s", date, month, year);
    String redColor = "rgb(220, 53, 69)";


    String studentName = firstName + " " + lastName,
            mobile = "Mobile " + userNumber,
            hobbies = sport + ", " + music,
            picture = "Picture test.jpg",
            stateCity = state + " " + city;


    @Test
    void submitStudentRegistrationFormFillAllFieldsTest() {


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth(month, year, date)
                .setSubjects(charSubject, subjects)
                .setHobby(sport)
                .setHobby(music)
                .uploadPicture(photo)
                .currentAddress(address)
                .setState(state)
                .selectCity(city)
                .submitButton();

        registrationPage.checkSubmitFormTitle()
                .tableRowCheck("Student Name", studentName)
                .tableRowCheck("Student Email", email)
                .tableRowCheck("Gender", gender)
                .tableRowCheck("Mobile", mobile)
                .tableRowCheck("Date of Birth", dateOfBirth)
                .tableRowCheck("Subjects", subjects)
                .tableRowCheck("Hobbies", hobbies)
                .tableRowCheck("Picture", picture)
                .tableRowCheck("Address", address)
                .tableRowCheck("State and City", stateCity);
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
        registrationPage.checkSubmitFormTitle()
                .tableRowCheck("Student Name", studentName)
                .tableRowCheck("Gender", gender)
                .tableRowCheck("Mobile", mobile)
                .tableRowCheck("Date of Birth", dateOfBirth);
    }

    @Test
    void submitStudentRegistrationFormFillRequiredFieldsNegativeTest() {
        registrationPage.openPage()
                .submitButton()
                .checkFirsNameInputColor("border-color", redColor)
                .checkLastNameInputColor("border-color", redColor)
                .checkGenderColor("border-color", redColor)
                .checkPhoneInputColor("border-color", redColor)
                .checkSubmitFormNotVisible();
    }

}
