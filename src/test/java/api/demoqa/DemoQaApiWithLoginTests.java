package api.demoqa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestConfig;
import helpers.WithLogin;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.Isbn;
import models.login.LoginRequestBodyModel;
import models.login.LoginResponseBodyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.ApiActions.LOGIN;
import static constants.Constants.BOOKS.BOOK_ISBN_JAVASCRIPT;
import static constants.Constants.CREDENTIALS.PASSWORD;
import static constants.Constants.CREDENTIALS.USER_NAME;
import static constants.Constants.Path.ACCOUNT_V1;
import static helpers.SupportRequest.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpecs.accountV1LoginRequestSpecification;
import static specs.LoginSpecs.accountV1LoginResponseSpecification;

@Tag("API")
public class DemoQaApiWithLoginTests extends TestConfig {


    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Успешная авторизация в личном кабинет")
    public void successfullyLoginToBookStoreTest() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        LoginResponseBodyModel response = step("Авторизация пользователя", () -> given(accountV1LoginRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(accountV1LoginResponseSpecification)
                .extract().as(LoginResponseBodyModel.class));

        step("Пользователь авторизовался успешно", () -> {
            compareValues(authBody.getUserName(), response.getUsername());
            compareValues("false", response.getIsActive());
        });
    }


    @Test
    @WithLogin
    @DisplayName("Успешное добавление книги в личный кабинет")
    public void AddItemToCartBookStoreTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Response authResponse = getResponse(USER_NAME, PASSWORD);

        String userId = authResponse.path("userId");
        String bearerToken = "Bearer " + getAuthorizationToken(USER_NAME, PASSWORD);
        List<Isbn> listIsbn = List.of(new Isbn(BOOK_ISBN_JAVASCRIPT));

        AddBookRequestModel bookData = new AddBookRequestModel(userId, listIsbn);

        clearBooks(bearerToken, userId, BOOK_ISBN_JAVASCRIPT);

        addBook(bearerToken, bookData);

        step("Открыта страница профиля", () -> {
            open("/profile");
        });

        step("Книга добавлена в профиль", () ->
                $(".ReactTable").shouldHave(text("Speaking JavaScript")));

    }


}
