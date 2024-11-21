package pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.Calendar;
import pages.components.CheckResults;

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


    Calendar calendar = new Calendar();
    CheckResults tableCheck = new CheckResults();
    CheckResults colorCheck = new CheckResults();

    public void removeAds() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Step("Открыть страницу")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        removeAds();
        return this;
    }

    @Step("Заполнить First Name {value}")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Заполнить Last Name {value}")
    public RegistrationPage setLastName(String value) {
        lastNameInput.type(value);
        return this;
    }

    @Step("Заполнить Email {value}")
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Заполнить Gender {value}")
    public RegistrationPage setGender(String value) {
        genderRadio.filterBy(text(value)).first().click();
        return this;
    }

    @Step("Заполнить Mobile Number {value}")
    public RegistrationPage setNumber(String value) {
        numberInput.setValue(value);
        return this;
    }

    @Step("Заполнить Date of Birth {month} {year} {date}")
    public RegistrationPage setDateOfBirth(String month, String year, String date) {
        dateOfBirthInput.click();
        calendar.setDate(month, year, date);
        return this;
    }

    @Step("Заполнить Subjects {subjects}")
    public RegistrationPage setSubjects(String value, String subjects) {
        subjectInput.setValue(value);
        selectSubject.filterBy(text(subjects)).first().click();
        return this;
    }

    @Step("Заполнить Hobbies {value}")
    public RegistrationPage setHobby(String value) {
        hobbies.filterBy(text(value)).first().click();
        return this;
    }

    @Step("Прикрепить Picture {value}")
    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    @Step("Заполнить Current Address {address}")
    public RegistrationPage currentAddress(String address) {
        currentAddress.setValue(address);
        return this;
    }

    @Step("Заполнить State {state}")
    public RegistrationPage setState(String state) {
        stateClick.click();
        selectState.filterBy(text(state)).first().click();
        return this;
    }

    @Step("Заполнить City {city}")
    public RegistrationPage selectCity(String city) {
        cityClick.click();
        selectCity.filterBy(text(city)).first().click();
        return this;
    }

    @Step("Форма отправлена")
    public RegistrationPage submitButton() {
        submit.click();
        return this;
    }

    @Step("Заполнить Email {value}")
    public RegistrationPage checkSubmitFormTitle() {
        submitFrom.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Введенное значение {key} совпало с ожидаемым {value}")
    public RegistrationPage tableRowCheck(String key, String value) {
        tableCheck.checkRow(key, value);
        return this;
    }

    @Step("Цвет cssElement {cssElement} совпало с ожидаемым значением {color}")
    public RegistrationPage checkFirsNameInputColor(String cssElement, String color) {
        colorCheck.checkElementColor(firstNameInput, cssElement, color);
        return this;
    }

    @Step("Цвет cssElement {cssElement} совпало с ожидаемым значением {color}")
    public RegistrationPage checkLastNameInputColor(String cssElement, String color) {
        colorCheck.checkElementColor(lastNameInput, cssElement, color);
        return this;
    }

    @Step("Цвет cssElement {cssElement} совпало с ожидаемым значением {color}")
    public RegistrationPage checkGenderColor(String cssElement, String color) {
        colorCheck.checkElementColor(gender, cssElement, color);
        return this;
    }

    @Step("Цвет cssElement {cssElement} совпало с ожидаемым значением {color}")
    public RegistrationPage checkPhoneInputColor(String cssElement, String color) {
        colorCheck.checkElementColor(numberInput, cssElement, color);
        return this;
    }

    public RegistrationPage checkSubmitFormNotVisible() {
        submitFrom.shouldNotBe(visible);
        return this;
    }

}
