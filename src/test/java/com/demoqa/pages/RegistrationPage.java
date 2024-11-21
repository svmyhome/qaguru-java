package com.demoqa.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponents;
import com.demoqa.pages.components.CssComponents;
import com.demoqa.pages.components.TableComponents;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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
            selectCity = $$("div[id^=react-select-4-option-");


    CalendarComponents calendar = new CalendarComponents();
    TableComponents tableCheck = new TableComponents();
    CssComponents colorCheck = new CssComponents();

    public void removeAds() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        removeAds();
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
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

    public RegistrationPage setDateOfBirth(LocalDate birthday) {
        dateOfBirthInput.click();
        calendar.setDate(String.format("%02d", birthday.getDayOfMonth()),
                birthday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                String.valueOf(birthday.getYear()));
        return this;
    }

    public RegistrationPage setSubjects(String subjects) {
        subjectInput.setValue(subjects).pressEnter();
        return this;
    }


    public RegistrationPage setHobby(String value) {
        hobbies.filterBy(text(value)).first().click();
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

    public RegistrationPage checkSubmitFormTitle() {
        submitFrom.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public RegistrationPage tableRowCheck(String key, String value) {
        tableCheck.checkRow(key, value);
        return this;
    }

    public RegistrationPage checkFirsNameInputColor(String cssElement, String color) {
        colorCheck.checkElementColor(firstNameInput, cssElement, color);
        return this;
    }

    public RegistrationPage checkLastNameInputColor(String cssElement, String color) {
        colorCheck.checkElementColor(lastNameInput, cssElement, color);
        return this;
    }

    public RegistrationPage checkGenderColor(String cssElement, String color) {
        colorCheck.checkElementColor(gender, cssElement, color);
        return this;
    }

    public RegistrationPage checkPhoneInputColor(String cssElement, String color) {
        colorCheck.checkElementColor(numberInput, cssElement, color);
        return this;
    }

    public RegistrationPage checkSubmitFormNotVisible() {
        submitFrom.shouldNotBe(visible);
        return this;
    }

}
