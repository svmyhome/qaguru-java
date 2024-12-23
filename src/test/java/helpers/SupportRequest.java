package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;
import models.books.Isbn;
import models.login.LoginRequestBodyModel;

import java.util.List;

import static constants.Constants.ApiActions.*;
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

    @Step("Удалить книги из профиля")
    public static void deleteBook(String token, String userId, Isbn isbn) {
        step("Удаление книг из профиля", () -> {// VERIFY THAT CART EMPTY
            List<String> response = given(accountV1LoginRequestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get(ACCOUNT_V1 + USER + userId)
                    .then()
                    .spec(accountV1LoginResponseSpecification)
                    .statusCode(200).extract().path("books");

            // DELETE BOOK
            if (response.size() == 1) {
                deleteOneBook(token, userId, isbn);
//                DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn.getIsbn(), userId);
//                given(deleteBookStoreV1LoginRequestSpecification)
//                        .header("Authorization", "Bearer " + token)
//                        .body(bookDataDelete)
//                        .when()
//                        .delete(BOOKSTORE_V1 + BOOK)
//                        .then()
//                        .spec(deleteBookStoreV1LoginResponseSpecification);
            } else if (response.size() > 1) {
                deleteAllBooks(token, userId);
//                given(deleteBookStoreV1LoginRequestSpecification)
//                        .header("Authorization", "Bearer " + token)
//                        .queryParam("UserId", userId)
//                        .when()
//                        .delete(BOOKSTORE_V1 + BOOKS)
//                        .then()
//                        .spec(deleteBookStoreV1LoginResponseSpecification);
            }
        });
    }

    @Step("Удалить одну книги из профиля")
    public static void deleteOneBook(String token, String userId, Isbn isbn) {
        step("Удаление одной книги из профиля", () -> {
            DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn.getIsbn(), userId);
            given(deleteBookStoreV1LoginRequestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .body(bookDataDelete)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOK)
                    .then()
                    .spec(deleteBookStoreV1LoginResponseSpecification);
        });
    }

    @Step("Удалить все книги из профиля")
    public static void deleteAllBooks(String token, String userId) {
        step("Удаление всех книг из профиля", () -> {
            given(deleteBookStoreV1LoginRequestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .queryParam("UserId", userId)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOKS)
                    .then()
                    .spec(deleteBookStoreV1LoginResponseSpecification);
        });
    }


}