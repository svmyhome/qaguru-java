package pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.Calendar;
import pages.components.CheckResults;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            numberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateClick = $("#state"),
            cityClick = $("#city"),
            submit = $("#submit"),
            submitFrom = $(".modal-title"),
            gender = $("#genterWrapper .custom-control-label");

    private final ElementsCollection genderRadio = $$("label[for^='gender-radio-']"),
            selectSubject = $$("div[id^='react-select-2-option-']"),
            hobbies = $$("label[for^='hobbies-checkbox']"),
            selectState = $$("div[id^=react-select-3-option-"),
            selectCity = $$("div[id^=react-select-4-option-"),
            resultTable = $$(".table-responsive tbody tr");

    Calendar calendar = new Calendar();
    CheckResults checkTable = new CheckResults();
    CheckResults checkColor = new CheckResults();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.type(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderRadio.filterBy(text(value)).first().click();
        return this;
    }

    public RegistrationPage setNumber(String value) {
        numberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String month, String year, String date) {
        dateOfBirthInput.click();
        calendar.setDate(month, year, date);
        return this;
    }

    public RegistrationPage setSubjects(String value, String subjects) {
        subjectInput.setValue(value);
        selectSubject.filterBy(text(subjects)).first().click();
        return this;
    }

    public RegistrationPage selectHobbies(String sport, String music) {
        hobbies.filterBy(text(sport)).first().click();
        hobbies.filterBy(text(music)).first().click();
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage currentAddress(String address) {
        currentAddress.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateClick.click();
        selectState.filterBy(text(state)).first().click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        cityClick.click();
        selectCity.filterBy(text(city)).first().click();
        return this;
    }

    public RegistrationPage submitButton() {
        submit.click();
        return this;
    }

    public RegistrationPage checkResultAllFields(String firstName, String lastName, String email, String gender,
                                                 String subjects, String sport, String music, String address,
                                                 String state, String city) {
        submitFrom.shouldHave(text("Thanks for submitting the form"));
        checkTable.checkTableResult(firstName, lastName, email, gender,
                subjects, sport, music, address, state, city);
        return this;
    }

    public RegistrationPage checkResultRequiredFields(String firstName, String lastName, String gender) {
        submitFrom.shouldHave(text("Thanks for submitting the form"));
        checkTable.checkRequiredFielsTableResult(firstName, lastName, gender);
        return this;
    }

    public RegistrationPage checkFirsNameInputColor(String cssElement, String color) {
//        firstNameInput.shouldHave((cssValue(cssElement, color)));
        checkColor.checkElementColor(firstNameInput, cssElement,color);
        return this;
    }

    public RegistrationPage checkLastNameInputColor(String cssElement, String color) {
        lastNameInput.shouldHave((cssValue(cssElement, color)));
        return this;
    }

    public RegistrationPage checkGenderColor(String cssElement, String color) {
        gender.shouldHave((cssValue(cssElement, color)));
        return this;
    }

    public RegistrationPage checkPhoneInputColor(String cssElement, String color) {
        numberInput.shouldHave((cssValue(cssElement, color)));
        return this;
    }

    public RegistrationPage checkSubmitFormNotVisible() {
        submitFrom.shouldNotBe(visible);
        return this;
    }

}
