package com.demoqa.pages;

import com.demoqa.pages.components.CheckResults;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    CheckResults checkResults = new CheckResults();

    public TextBoxPage openPage() {
        open("https://demoqa.com/text-box");
        return this;
    }

    public TextBoxPage fillFullName(String fio) {
        $("#userName").setValue(fio);
        return this;
    }

    public TextBoxPage fillEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }

    public TextBoxPage fillCurrentAddress(String currentAddress) {
        $("#currentAddress").setValue(currentAddress);
        return this;
    }

    public TextBoxPage fillPermanentAddress(String permanentAddress) {
        $("#permanentAddress").setValue(permanentAddress);
        return this;
    }

    public TextBoxPage clickSubmit() {
        $("#submit").click();
        return this;
    }

    public TextBoxPage checkTextBoxString(String value) {
        checkResults.checkTextBoxResults(value);
        return this;
    }
}
