package api.demoqa;

import com.codeborne.selenide.Selenide;
import config.TestConfig;
import helpers.Attach;
import helpers.WithLogin;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.Isbn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.Books.BOOK_ISBN_JAVASCRIPT;
import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;
import static helpers.SupportRequest.*;
import static io.qameta.allure.Allure.step;

@Tag("API")
@DisplayName("API + UI")
public class DemoQaApiUiWithLoginTests extends TestConfig {

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
        getProfileInfo(bearerToken, userId);

        addBook(bearerToken, bookData);

        clearBooks(bearerToken, userId, BOOK_ISBN_JAVASCRIPT);

        step("Открыта страница профиля", () -> {
            open("/profile");
        });

        step("Книга удалена из профиля", () ->
                $(".ReactTable").shouldNotHave(text("Speaking JavaScript")));
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

        step("Открыта страница профиля", () -> {
            open("/profile");
        });

        step("Книга добавлена в профиль", () ->
                $(".ReactTable").shouldHave(text("Speaking JavaScript")));

    }
    // TODO скриншоты
    // TODO чтобы было видно в селениеде включить
    // gradle api_test -Dremote=https://user1:1234@selenoid.autotests.cloud/wd/hub -Dbrowser=chrome -DbrowserVersion=126.0 -DbrowserSize=1000x2000


}
