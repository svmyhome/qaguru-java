package allure;

import allure.model.WebSteps;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Feature("ISSUE в репозитории")
@Story("Создание ISSUE с аннотацией steps")
@Owner("sarychev")
@Link(value = "Issue JIRA", url = "https://Jira.com/789")
public class AllureWebStepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 92;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Issue c номером " + ISSUE + " существует в " + REPOSITORY)
    public void issueShouldExistAnnotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot(); // отдельный шаг для создания скриншота
        steps.searchForRepository(REPOSITORY);
        steps.clickForLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssue(ISSUE);
        steps.takeScreenshot();// отдельный шаг для создания скриншота

    }
}
