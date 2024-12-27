package pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class CookieManagement {

    @Step("{nameCookie}")
    public void setCookie(String nameCookie, String valueCookie) {
        step(nameCookie + " : " + valueCookie, () -> {
            getWebDriver().manage().addCookie(new Cookie(nameCookie, valueCookie));
        });
    }
}
