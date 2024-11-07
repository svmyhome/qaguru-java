package com.lenta;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends TestBase {


    @Test
    void LoginTest() {
        open("/");
        $("[automation-id='login-button']").click();
        $("[automation-id=dialog-auth]").shouldHave(text("Войти или зарегистрироваться"));
        $("input[value='Получить код']").shouldBe(disabled);
        $("input[type=tel]").setValue("9535006559");
        $("input[value='Получить код']").shouldNotBe(disabled);
    }
}
