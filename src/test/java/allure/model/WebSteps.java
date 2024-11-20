package allure.model;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {


    @Step("Открыть главную страницу Github")
    public void openMainPage() {
        open("https://github.com");
    }


    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").pressEnter();
    }

    @Step("Переход в репозиторий {repo}")
    public void clickForLink(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step("Открыть таб Issues")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Issues с номером {issue} существует")
    public void shouldSeeIssue(int issue) {
        $("#issue_" + issue).should(exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
