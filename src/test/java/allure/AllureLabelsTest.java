package allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllureLabelsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 92;

    @Test
    @Feature("ISSUE в репозитории")
    @Story("Создание ISSUE")
    @Owner("sarychev")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://google.com")
    @DisplayName("Создание ISSUE для авторизованного пользователя")
    public void staticLabelsTest() {

    }

    @Test
    public void dynamicLabelsTest() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание ISSUE для авторизованного пользователя")
        );
        Allure.feature("ISSUE в репозитории");
        Allure.story("Создание ISSUE");
        Allure.label("owner", "sarychev");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.label("Testing", "https://testing.com`");
    }

}
