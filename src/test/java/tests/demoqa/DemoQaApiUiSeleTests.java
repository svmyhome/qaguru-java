package tests.demoqa;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Tag("API")
@Tag("full")
@Owner("Ivan Ivanov")
@Feature("Feature Automation")
@DisplayName("API + UI")
public class DemoQaApiUiSeleTests extends TestBase1 {
    @DisplayName("Успешное удаление одной книги из личного кабинета")
    @Test
    public void deleteItemFromCartBookStoreTest() {
        open("/register");
        $("#firstname").setValue("petr");
    }
}
