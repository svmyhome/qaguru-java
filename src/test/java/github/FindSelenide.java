package github;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FindSelenide {

    @Test
    void findSelenidTest() {
        open("https://github.com/selenide/selenide");
        $("a#wiki-tab").click();
//        $("#repository-container-header nav ul li a#wiki-tab").click();
        SelenideElement pages = $("#wiki-pages-filter");
        pages.click();
        pages.setValue("SoftAssertions");
        SelenideElement rightbar = $(".wiki-rightbar");
        rightbar.shouldHave(text("SoftAssertions"));
            rightbar.find(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text("JUnit5"));
    }
}
