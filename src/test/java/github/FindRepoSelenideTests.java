package github;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FindRepoSelenideTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize="1980x1200";
        Configuration.baseUrl="https://github.com";
        Configuration.pageLoadStrategy="eager";
    }
    @Test
    void findJunitFromSelenideTest() {
        open("/selenide/selenide");
        $("a#wiki-tab").click();
        SelenideElement pages = $("#wiki-pages-filter");
        pages.click();
        pages.setValue("SoftAssertions");
        SelenideElement rightbar = $(".wiki-rightbar");
        rightbar.shouldHave(text("SoftAssertions"));
        rightbar.find(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text("JUnit5"));
        $("#wiki-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """));
    }
}
