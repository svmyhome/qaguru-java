package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.login.LoginRequestBodyModel;

import static constants.Constants.ApiActions.BOOKS;
import static constants.Constants.ApiActions.LOGIN;
import static constants.Constants.Path.ACCOUNT_V1;
import static constants.Constants.Path.BOOKSTORE_V1;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.LoginSpecs.*;

public class SupportRequest {

    @Step("Получить респонс для {userName} ")
    public static Response getRequest(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        return step("Authorize user", () -> given(accountV1LoginRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(accountV1LoginResponseSpecification)
                .body("username", is(userName)).extract().response());
    }

    @Step("Получить авторизационный токен для {userName} ")
    public static String getAuthorizationToken(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        return step("Authorize user", () -> given(accountV1LoginRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(accountV1LoginResponseSpecification)
                .body("username", is(userName)).extract().response().path("token"));
    }

    @Step("Добавить книгу")
    public static void addBook(String token, AddBookRequestModel bookData) {
        step("Добавлена книга с ISBN {bookIsbn}", () -> {
            given(bookStoreV1LoginRequestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .body(bookData)
                    .when()
                    .post(BOOKSTORE_V1 + BOOKS)
                    .then()
                    .spec(bookStoreV1LoginResponseSpecification);
        });
    }

}
