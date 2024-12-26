package tests.demoqa;

import com.codeborne.selenide.Selenide;
import helpers.Attach;
import helpers.WithLogin;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.Isbn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import tests.TestBase;

import java.util.List;

import static constants.Constants.Books.BOOK_ISBN_JAVASCRIPT;
import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;
import static helpers.SupportRequest.*;

@Tag("API")
@DisplayName("API + UI")
public class DemoQaApiUiWithLoginTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    public static final String BOOK = "Speaking JavaScript";

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Финальный скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }


    @Test
    @WithLogin
    @DisplayName("Успешное удаление одной книги из личного кабинета")
    public void deleteItemFromCartBookStoreTest() {
        Response authResponse = getResponse(USER_NAME, PASSWORD);
        String userId = authResponse.path("userId");
        String bearerToken = "Bearer " + getAuthorizationToken(USER_NAME, PASSWORD);
        List<Isbn> listIsbn = List.of(new Isbn(BOOK_ISBN_JAVASCRIPT));
        AddBookRequestModel bookData = new AddBookRequestModel(userId, listIsbn);

        clearBooks(bearerToken, userId, BOOK_ISBN_JAVASCRIPT);
        getProfileInfo(bearerToken, userId); // TODO точно нужно?
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
        String bearerToken = "Bearer " + getAuthorizationToken(USER_NAME, PASSWORD);
        List<Isbn> listIsbn = List.of(new Isbn(BOOK_ISBN_JAVASCRIPT));

        AddBookRequestModel bookData = new AddBookRequestModel(userId, listIsbn);
        clearBooks(bearerToken, userId, BOOK_ISBN_JAVASCRIPT);
        addBook(bearerToken, bookData);

        profilePage.openProfilePage(USER_NAME).assertBookExistInProfile(BOOK);
    }

}
