package pages;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.ApiActions.PROFILE;
import static constants.Constants.Credentials.USER_NAME;
import static io.qameta.allure.Allure.step;

public class ProfilePage {

    @Step("Открыт профиль {userName}")
    public ProfilePage openProfilePage(String userName) {
        step("Открыта страница профиля", () -> {
            open(PROFILE);
        });
        step("Профиль {userName}", () ->
                $("#userName-value").shouldHave(text(USER_NAME)));
        return this;
    }

    @Step("В профиле есть книга {book}")
    public ProfilePage bookExistFromProfile(String book) {
        $(".ReactTable").shouldHave(text(book));
        return this;
    }

    @Step("Удаление книги из профиля")
    public ProfilePage deleteBook() {
        step("Кликнуть на иконку корзины", () -> {
            $("#delete-record-undefined").click();
        });
        step("Подтвердить удаление книги", () -> {
            $("#closeSmallModal-ok").click();
        });
        return this;
    }

    @Step("Книга {book} удалена из профиля")
    public ProfilePage assertDeleteBook(String book) {
        $(".ReactTable").shouldNotHave(text(book));
        return this;
    }

}
