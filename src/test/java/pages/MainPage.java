package pages;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import pages.components.CookieManagement;

import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.ApiActions.FAVICON;
import static io.qameta.allure.Allure.step;

public class MainPage {
    CookieManagement setCookie = new CookieManagement();


    @Step("Открыта страница" + FAVICON)
    public MainPage openMainPage() {
        open(FAVICON);
        return this;
    }

    @Step("Установлены авторизационные cookies")
    public MainPage setAuthorizationCookies(Response response) {
        step("Cookies", () -> {
            setCookie.setCookie("userID", response.path("userId"));
            setCookie.setCookie("expires", response.path("expires"));
            setCookie.setCookie("token", response.path("token"));
        });
        return this;
    }

    @Step("Авторизоваться в браузере с использованием Cookies")
    public MainPage openFaviconPage(Response response) {
        openMainPage().setAuthorizationCookies(response);
        return this;
    }

}
