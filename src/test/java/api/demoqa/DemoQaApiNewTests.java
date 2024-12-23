package api.demoqa;

import com.codeborne.selenide.Selenide;
import config.TestConfig;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;
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
import static constants.Constants.ApiActions.*;
import static constants.Constants.CREDENTIALS.PASSWORD;
import static constants.Constants.CREDENTIALS.USER_NAME;
import static constants.Constants.Path.ACCOUNT_V1;
import static constants.Constants.Path.BOOKSTORE_V1;
import static helpers.SupportRequest.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.account_v1_login_request_specification;
import static specs.LoginSpecs.account_v1_login_response_specification;

@Tag("API")
public class DemoQaApiNewTests extends TestConfig {


    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    String firstName = "Vin";
    String lastName = "Dsel";

    @Test
    public void AuthorizeDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        LoginResponseBodyModel response = step("Authorize user", () -> given(account_v1_login_request_specification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(account_v1_login_response_specification)
                .extract().as(LoginResponseBodyModel.class));

        step("Assert that ", () -> {
            assertEquals(authBody.getUserName(), response.getUsername());
            assertEquals("false", response.getIsActive());
        });
    }

    @Test
    public void AddItemToCartDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        Response authResponse = getRequest(USER_NAME, PASSWORD);
        String authResponse1 = getAuthorizationToken(USER_NAME, PASSWORD);

        String userId = authResponse.path("userId");
        String expires = authResponse.path("expires");
        String token = getAuthorizationToken(USER_NAME, PASSWORD);
        String bookIsbn = "9781449365035";
        Isbn isbn = new Isbn(bookIsbn);
        List<Isbn> listIsbn = List.of(isbn);
        AddBookRequestModel bookData1 = new AddBookRequestModel(userId, listIsbn);

        // VERIFY THAT CART EMPTY
        List<String> response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ACCOUNT_V1 + USER + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200).extract().path("books");


        // DELETE BOOK
        if (response.size() == 1) {
            DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn.getIsbn(), userId);
            given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(bookDataDelete)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOK)
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(204);
        } else if (response.size() > 1) {
            given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + token)
                    .queryParam("UserId", userId)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOKS)
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(204);
        }


        // ADD BOOK

        addBook(token, bookData1); // TODO Возможно не стоит убирать мы же тест на добавление как раз делдаем
//        step("Добавлена книга с ISBN {bookIsbn}", () -> {
//            given(bookStoreV1LoginRequestSpecification)
//                    .header("Authorization", "Bearer " + token)
//                    .body(bookData1)
//                    .when()
//                    .post(BOOKSTORE_V1 + BOOKS)
//                    .then()
//                    .spec(bookStoreV1LoginResponseSpecification);
//        });


        // ASSERT FROM UI
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
