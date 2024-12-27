package pages;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import pages.components.CookieManagement;

import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.ApiActions.FAVICON;
import static io.qameta.allure.Allure.step;

public class MainPage {
    CookieManagement setCookie = new CookieManagement();

    @Step("Установить авторизационные Cookies")
    public MainPage openFaviconPage(Response response) {
        step("Открыта страница " + FAVICON, () -> {
            open(FAVICON);
        });
        step("Установлены авторизационные cookies", () -> {
            setCookie.setCookie("userID", response.path("userId"));
            setCookie.setCookie("expires", response.path("expires"));
            setCookie.setCookie("token", response.path("token"));
        });
        return this;
    }

}
