package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;
import models.login.LoginRequestBodyModel;

import java.util.List;

import static constants.Constants.ApiActions.*;
import static constants.Constants.Path.ACCOUNT_V1;
import static constants.Constants.Path.BOOKSTORE_V1;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.*;

public class SupportRequest {

    @Step("Получить респонс для  пользователя {userName} ")
    public static Response getResponse(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        return step("Запрос и ответ", () -> given(baseRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(statusCode200ResponseSpecification)
                .body("username", is(userName)).extract().response());
    }

    @Step("Получить авторизационный токен для пользователя {userName}")
    public static String getAuthorizationToken(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        return step("Запрос и ответ", () -> given(baseRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(statusCode200ResponseSpecification)
                .body("username", is(userName)).extract().response().path("token"));
    }

    @Step("Добавить книгу в профиль")
    public static void addBook(String bearerToken, AddBookRequestModel bookData) {
        step("Книга добавлена", () -> {
            given(baseRequestSpecification)
                    .header("Authorization", bearerToken)
                    .body(bookData)
                    .when()
                    .post(BOOKSTORE_V1 + BOOKS)
                    .then()
                    .spec(statusCode201ResponseSpecification);
        });
    }

    @Step("Очистка профиля от книг")
    public static void clearBooks(String bearerToken, String userId, String isbn) {
        DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn, userId);
        step("Очистка профиля от книг", () -> {
            List<String> response = getProfileInfo(bearerToken, userId);
            if (response.size() == 1) {
                deleteOneBook(bearerToken, bookDataDelete);
            } else if (response.size() > 1) {
                deleteAllBooks(bearerToken, userId);
            }
        });
    }

    @Step("Запрос информации профиля")
    public static List<String> getProfileInfo(String bearerToken, String userId) {
        return given(baseRequestSpecification)
                .header("Authorization", bearerToken)
                .when()
                .get(ACCOUNT_V1 + USER + userId)
                .then()
                .spec(statusCode200ResponseSpecification)
                .statusCode(200).extract().path("books");
    }

    @Step("Удалить одну книги из профиля")
    public static void deleteOneBook(String bearerToken, DeleteBookRequestModel bookDataDelete) {
        step("Удаление одной книги из профиля", () -> {
            given(baseRequestSpecification)
                    .header("Authorization", bearerToken)
                    .body(bookDataDelete)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOK)
                    .then()
                    .spec(statusCode204ResponseSpecification);
        });
    }

    @Step("Удалить все книги из профиля")
    public static void deleteAllBooks(String bearerToken, String userId) {
        step("Удаление всех книг из профиля", () -> {
            given(baseRequestSpecification)
                    .header("Authorization", bearerToken)
                    .queryParam("UserId", userId)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOKS)
                    .then()
                    .spec(statusCode204ResponseSpecification);
        });
    }

    @Step("Значение совпадает c {expected}")
    public static void compareValues(String expected, String actual) {
        assertEquals(expected, actual);
    }


}
