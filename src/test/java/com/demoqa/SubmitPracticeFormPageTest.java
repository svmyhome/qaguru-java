package com.demoqa;

import com.demoqa.helpers.RandomUtils;
import com.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SubmitPracticeFormPageTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

    String firstName = randomUtils.getRandomFirstName();
    String lastName = randomUtils.getRandomLastName();
    String email = randomUtils.getRandomEmail();

    String userNumber = randomUtils.getRandomPhoneNumber(10);
    String subjects = randomUtils.getRandomSubjects();
    String address = randomUtils.getRandomAddress();
    String gender = randomUtils.getRandomGender();
    String hobby = randomUtils.getRandomHobby();
    String state = randomUtils.getRandomState();
    String city = randomUtils.getRandomCity(state);
    String photo = randomUtils.getRandomPicture();
    LocalDate dateOfBirth = randomUtils.getRandomBirthDay(15, 70);
    String redColor = "rgb(220, 53, 69)";


    @Test
    void submitStudentRegistrationFormFillAllFieldsTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth(dateOfBirth)
                .setSubjects(subjects)
                .setHobby(hobby)
                .uploadPicture(photo)
                .currentAddress(address)
                .setState(state)
                .selectCity(city)
                .submitButton();

        registrationPage.checkSubmitFormTitle()
                .tableRowCheck("Student Name", firstName + " " + lastName)
                .tableRowCheck("Student Email", email)
                .tableRowCheck("Gender", gender)
                .tableRowCheck("Mobile", "Mobile " + userNumber)
                .tableRowCheck("Date of Birth", dateOfBirth.format(formatter))
                .tableRowCheck("Subjects", subjects)
                .tableRowCheck("Hobbies", hobby)
                .tableRowCheck("Picture", "Picture " + photo)
                .tableRowCheck("Address", address)
                .tableRowCheck("State and City", state + " " + city);
    }

    @Test
    void submitStudentRegistrationFormFillRequiredFieldsTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth(dateOfBirth)
                .submitButton();
        registrationPage.checkSubmitFormTitle()
                .tableRowCheck("Student Name", firstName + " " + lastName)
                .tableRowCheck("Gender", gender)
                .tableRowCheck("Mobile", "Mobile " + userNumber)
                .tableRowCheck("Date of Birth", dateOfBirth.format(formatter));
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
