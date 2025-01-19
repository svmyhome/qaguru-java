package tests.bowserstack;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.lang.invoke.StringConcatFactory;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchFromWikipediaAndroidTest extends TestBase {

    @Test
    public void WikipediaTest() {
        String searchValue = "Appium";
        step("Find " + searchValue, () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchValue);
        });
        step("Verify value", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
        });

    }


    @Test
    public void FindSeleniumTest() {
        String searchValue = "Ubuntu";
        String expectedResult = "Linux distribution developed by Canonical";
        step("Find " + searchValue, () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchValue);
        });
        step("Verify value", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
            $(id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text(expectedResult)).click();
        });


    }


}
