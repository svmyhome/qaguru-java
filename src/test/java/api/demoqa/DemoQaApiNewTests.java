package api.demoqa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.Isbn;
import models.login.LoginRequestBodyModel;
import models.login.LoginResponseBodyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static constants.Constants.ApiActions.LOGIN;
import static constants.Constants.CREDENTIALS.PASSWORD;
import static constants.Constants.CREDENTIALS.USER_NAME;
import static constants.Constants.Path.ACCOUNT_V1;
import static helpers.SupportRequest.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.accountV1LoginRequestSpecification;
import static specs.LoginSpecs.accountV1LoginResponseSpecification;

@Tag("API")
public class DemoQaApiNewTests extends TestConfig {


    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    public void AuthorizeDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        LoginResponseBodyModel response = step("Authorize user", () -> given(accountV1LoginRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(accountV1LoginResponseSpecification)
                .extract().as(LoginResponseBodyModel.class));

        step("Assert that ", () -> {
            assertEquals(authBody.getUserName(), response.getUsername());
            assertEquals("false", response.getIsActive());
        });
    }

    @Test
    public void AddItemToCartDemoQa() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Response authResponse = getRequest(USER_NAME, PASSWORD);

        String userId = authResponse.path("userId");
        String expires = authResponse.path("expires");
        String token = getAuthorizationToken(USER_NAME, PASSWORD);
        String bearerToken = "Bearer " + getAuthorizationToken(USER_NAME, PASSWORD);
        String bookIsbn = "9781449365035";
        Isbn isbn = new Isbn(bookIsbn);
        List<Isbn> listIsbn = List.of(isbn);

        AddBookRequestModel bookData1 = new AddBookRequestModel(userId, listIsbn);

        clearBooks(bearerToken, userId, isbn);

        addBook(bearerToken, bookData1); // TODO Возможно не стоит убирать мы же тест на добавление как раз делдаем// ASSERT FROM UI
        step("Открыта страница профиля и подложены куки", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", userId));
            getWebDriver().manage().addCookie(new Cookie("expires", expires));
            getWebDriver().manage().addCookie(new Cookie("token", token));
            open("/profile");
        });

        step("В профиле есть книга", () ->
                $(".ReactTable").shouldHave(text("Speaking JavaScript")));

    }


}
