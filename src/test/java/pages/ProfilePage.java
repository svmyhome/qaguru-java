package pages;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.ApiActions.PROFILE;
import static constants.Constants.Credentials.USER_NAME;

public class ProfilePage {

    @Step("Открыт профиль пользователя {userName}")
    public ProfilePage openProfile(String userName) {
        openProfilePage().assertProfileBelongsToUser(userName);
        return this;
    }

    @Step("Открыта страница профиля")
    public ProfilePage openProfilePage() {
        open(PROFILE);
        return this;
    }

    @Step("Профиль принадлежит пользователю {userName}")
    public ProfilePage assertProfileBelongsToUser(String userName) {
        $("#userName-value").shouldHave(text(USER_NAME));
        return this;
    }


    @Step("Книга {book} есть в профиле")
    public ProfilePage assertBookExistInProfile(String book) {
        $(".ReactTable").shouldHave(text(book));
        return this;
    }

    @Step("Кликнуть на иконку корзины")
    public ProfilePage clickOnTrashBin() {
        $("#delete-record-undefined").click();
        return this;
    }

    @Step("Подтвердить удаление книги")
    public ProfilePage confirmingBookDeletion() {
        $("#closeSmallModal-ok").click();
        return this;
    }


    @Step("Удаление книги из профиля")
    public ProfilePage deleteBook() {
        clickOnTrashBin().confirmingBookDeletion();
        return this;
    }

    @Step("Книга {book} удалена из профиля")
    public ProfilePage assertBookIsDeleted(String book) {
        $(".ReactTable").shouldNotHave(text(book));
        return this;
    }
}
