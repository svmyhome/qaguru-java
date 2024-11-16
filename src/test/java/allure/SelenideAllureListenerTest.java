package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@Feature("ISSUE в репозитории")
@Story("Создание ISSUE с listener selenide")
@Owner("sarychev")
@Link(value = "Issue JIRA", url = "https://Jira.com/789")
public class SelenideAllureListenerTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 92;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Issue c номером " + ISSUE + " существует в " + REPOSITORY)
    public void issueShouldExistListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").pressEnter();

        $(By.linkText("eroshenkoam/allure-example")).click();

        $("#issues-tab").click();

        $("#issue_" + ISSUE).should(exist);

        System.out.println();
    }

}
