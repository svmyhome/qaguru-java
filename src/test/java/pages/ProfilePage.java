package pages;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.ApiActions.PROFILE;
import static constants.Constants.Credentials.USER_NAME;
import static io.qameta.allure.Allure.step;

public class ProfilePage {

    @Step("Открыт профиль пользователя {userName}")
    public ProfilePage openProfilePage(String userName) {
        step("Открыта страница профиля", () -> {
            open(PROFILE);
        });
        step("Профиль принадлежит пользователю " + userName, () ->
                $("#userName-value").shouldHave(text(USER_NAME)));
        return this;
    }

    @Step("Книга {book} есть в профиле")
    public ProfilePage assertBookExistInProfile(String book) {
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
    public ProfilePage assertBookIsDeleted(String book) {
        $(".ReactTable").shouldNotHave(text(book));
        return this;
    }
}
