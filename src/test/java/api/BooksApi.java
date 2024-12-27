package api;

import io.qameta.allure.Step;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;

import java.util.List;

import static api.AccountApi.getProfileInfo;
import static constants.Constants.ApiActions.BOOKSTORE_V1_BOOK;
import static constants.Constants.ApiActions.BOOKSTORE_V1_BOOKS;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpecs.*;

public class BooksApi {

    @Step("Добавить книгу в профиль")
    public static void addBook(String bearerToken, AddBookRequestModel bookData) {
        step("Книга добавлена", () -> {
            given(baseRequestSpecification)
                    .header("Authorization", bearerToken)
                    .body(bookData)
                    .when()
                    .post(BOOKSTORE_V1_BOOKS)
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

    @Step("Удалить одну книги из профиля")
    public static void deleteOneBook(String bearerToken, DeleteBookRequestModel bookDataDelete) {
        step("Удаление одной книги из профиля", () -> {
            given(baseRequestSpecification)
                    .header("Authorization", bearerToken)
                    .body(bookDataDelete)
                    .when()
                    .delete(BOOKSTORE_V1_BOOK)
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
                    .delete(BOOKSTORE_V1_BOOKS)
                    .then()
                    .spec(statusCode204ResponseSpecification);
        });
    }
}
