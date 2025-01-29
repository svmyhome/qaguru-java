package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Manual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("Manual")
@DisplayName("API")
@Feature("Feature 222222")
public class ManualTests {

    @Test
    @Manual
    @DisplayName("Ручной тест проверка")
    public void testAuth() {
        step("Открыть главную страницу");
        step("Авторизовать волдемара", () -> {
            step("Ввести логин");
            step("Ввести пароль");
        });
        step("Еще шагииии");
    }
}
