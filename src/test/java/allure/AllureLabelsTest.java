package allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("ISSUE в репозитории")
@Story("Создание ISSUE")
@Owner("sarychev")
@Link(value = "Issue JIRA", url = "https://Jira.com/123")
public class AllureLabelsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 92;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Issue c номером " + ISSUE + " существует в " + REPOSITORY)
    public void staticLabelsTest() {

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
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
