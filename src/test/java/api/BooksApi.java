package api;

import io.qameta.allure.Step;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;

import java.util.List;

import static api.AccountApi.getProfileInfo;
import static constants.Constants.ApiActions.BOOKSTORE_V1_BOOK;
import static constants.Constants.ApiActions.BOOKSTORE_V1_BOOKS;
import static io.restassured.RestAssured.given;
import static specs.LoginSpecs.*;

public class BooksApi {

    @Step("Книга добавлена в профиль")
    public static void addBook(String bearerToken, AddBookRequestModel bookData) {
        given(baseRequestSpecification)
                .header("Authorization", bearerToken)
                .body(bookData)
                .when()
                .post(BOOKSTORE_V1_BOOKS)
                .then()
                .spec(statusCode201ResponseSpecification);
    }

    @Step("Профиль очищен от книг")
    public static void clearBooks(String bearerToken, String userId, String isbn) {
        DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn, userId);
        List<String> response = getProfileInfo(bearerToken, userId);
        if (response.size() == 1) {
            deleteOneBook(bearerToken, bookDataDelete);
        } else if (response.size() > 1) {
            deleteAllBooks(bearerToken, userId);
        }
    }

    @Step("Одна книга удалена из профиля")
    public static void deleteOneBook(String bearerToken, DeleteBookRequestModel bookDataDelete) {
        given(baseRequestSpecification)
                .header("Authorization", bearerToken)
                .body(bookDataDelete)
                .when()
                .delete(BOOKSTORE_V1_BOOK)
                .then()
                .spec(statusCode204ResponseSpecification);
    }

    @Step("Все книги удалены из профиля")
    public static void deleteAllBooks(String bearerToken, String userId) {
        given(baseRequestSpecification)
                .header("Authorization", bearerToken)
                .queryParam("UserId", userId)
                .when()
                .delete(BOOKSTORE_V1_BOOKS)
                .then()
                .spec(statusCode204ResponseSpecification);
    }
}
