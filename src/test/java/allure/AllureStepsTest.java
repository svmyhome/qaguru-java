package allure;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

public class AllureStepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 92;

    @Test
    public void lambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();

            attachment("Source", webdriver().driver().source());
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();

            attachment("Source", webdriver().driver().source());
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        });

        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
            attachment("Source", webdriver().driver().source());
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        });

        step("Проверяем наличие Issues с номером " + ISSUE, () -> {
            $("#issue_" + ISSUE).should(exist);
            attachment("Source", webdriver().driver().source()); // прикладывает аттачментс
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        });
    }

    @Test
    public void webAnnotatedStepsTest() {
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
