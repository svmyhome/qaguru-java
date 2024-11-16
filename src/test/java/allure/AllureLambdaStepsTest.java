package allure;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;


@Feature("ISSUE в репозитории")
@Story("Создание ISSUE с лямда шагами")
@Owner("sarychev")
@Link(value = "Issue JIRA", url = "https://Jira.com/456")
public class AllureLambdaStepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 92;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Issue c номером " + ISSUE + " существует в " + REPOSITORY)
    public void issueShouldExistLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issues с номером " + ISSUE, () -> {
            $("#issue_" + ISSUE).should(exist);
            attachment("Source", webdriver().driver().source()); // прикладывает аттачментс
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        });
    }


}
