package com.lenta.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginButton = $("[automation-id='login-button']"),
            authorizationWindow = $("[automation-id=dialog-auth]"),
            getCodeButton = $("input[value='Получить код']"),
            phoneInput = $("input[type=tel]");

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public LoginPage shouldAuthorizationWindowOpen() {
        authorizationWindow.shouldHave(text("Войти или зарегистрироваться"));
        return this;
    }

    public LoginPage shouldCodeButtonDisable() {
        getCodeButton.shouldBe(disabled);
        return this;
    }

    public LoginPage setPhoneNumber() {
        phoneInput.setValue("9535006559");
        return this;
    }

    public LoginPage shouldCodeButtonEnable() {
        getCodeButton.shouldNotBe(disabled);
        return this;
    }
}
