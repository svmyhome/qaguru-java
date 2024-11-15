package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideAllureTest {

    @Test
    public void SelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").pressEnter();

        $(By.linkText("eroshenkoam/allure-example")).click();

        $("#issues-tab").click();

        $("#issue_93").should(exist);

        System.out.println();
    }

}
