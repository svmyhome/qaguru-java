package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Manual;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("Manual")
@Owner("Sidor Sidoro")
@DisplayName("UI")
@Feature("Feature Manual")
public class ManualTests {

    @Test
    @Manual
    @DisplayName("Ручной тест")
    public void testAuth() {
        step("Открыть главную страницу");
        step("Авторизовать пользователем", () -> {
            step("Ввести логин");
            step("Ввести пароль");
        });
        step("Переход в профиль выполнен");
    }
}
