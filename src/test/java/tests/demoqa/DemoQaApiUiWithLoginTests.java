package tests.demoqa;

import helpers.WithLogin;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.Isbn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import tests.TestBase;

import java.util.List;

import static api.AccountApi.getResponse;
import static api.BooksApi.addBook;
import static api.BooksApi.clearBooks;
import static constants.Constants.Books.BOOK_ISBN_JAVASCRIPT;
import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;

@Tag("API")
@Tag("full")
@DisplayName("API + UI")
public class DemoQaApiUiWithLoginTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    public static final String BOOK = "Speaking JavaScript";

    @Test
    @WithLogin
    @DisplayName("Успешное удаление одной книги из личного кабинета")
    public void deleteItemFromCartBookStoreTest() {
        Response authResponse = getResponse(USER_NAME, PASSWORD);
        String userId = authResponse.path("userId");
        String bearerToken = "Bearer " + authResponse.path("token");
        List<Isbn> listIsbn = List.of(new Isbn(BOOK_ISBN_JAVASCRIPT));
        AddBookRequestModel bookData = new AddBookRequestModel(userId, listIsbn);
        clearBooks(bearerToken, userId, BOOK_ISBN_JAVASCRIPT);
        addBook(bearerToken, bookData);

        profilePage.openProfilePage(USER_NAME)
                .assertBookExistInProfile(BOOK)
                .deleteBook()
                .assertBookIsDeleted(BOOK);
    }

    @Test
    @WithLogin
    @DisplayName("Успешное добавление книги в личный кабинет")
    public void addItemToCartBookStoreTest() {
        Response authResponse = getResponse(USER_NAME, PASSWORD);
        String userId = authResponse.path("userId");
        String bearerToken = "Bearer " + authResponse.path("token");
        List<Isbn> listIsbn = List.of(new Isbn(BOOK_ISBN_JAVASCRIPT));

        AddBookRequestModel bookData = new AddBookRequestModel(userId, listIsbn);
        clearBooks(bearerToken, userId, BOOK_ISBN_JAVASCRIPT);
        addBook(bearerToken, bookData);

        profilePage.openProfilePage(USER_NAME).assertBookExistInProfile(BOOK);
    }

}
