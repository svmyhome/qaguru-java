package tests.local;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTests extends TestBaseLocal {
    @Test
    void successfulSearchTest() {
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(partialText("The Free Encyclopedia"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Data & Privacy"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();

        $(id("org.wikipedia.alpha:id/view_announcement_text")).shouldHave(partialText("Customize your Explore feed"));


        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Ubuntu");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}
