package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

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
}
