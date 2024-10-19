package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindSolutionsTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void hoverSolutionTest() {

        open("/");
        $$(".HeaderMenu-nav li").findBy(text("Solutions")).hover();
        $$(".HeaderMenu-dropdown a").findBy(text("Enterprises")).click();
        $$("div h2").findBy(text("enterprise-ready")).shouldHave(text("enterprise-ready"));
        assertEquals("https://github.com/enterprise", url());
    }


}
