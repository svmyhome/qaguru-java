package github;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FindRepoSelenideTests {

    @Test
    void findJunitFromSelenideTest() {
        open("https://github.com/selenide/selenide");
        $("a#wiki-tab").click();
        SelenideElement pages = $("#wiki-pages-filter");
        pages.click();
        pages.setValue("SoftAssertions");
        SelenideElement rightbar = $(".wiki-rightbar");
        rightbar.shouldHave(text("SoftAssertions"));
        rightbar.find(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text("JUnit5"));
    }
}
