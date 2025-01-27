package screens.android;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class SearchStepsScreen {
    private final SelenideElement searchTab = $(accessibilityId("Search Wikipedia")),
            announcementText = $(id("org.wikipedia.alpha:id/view_announcement_text"));

    @Step("Open main screen")
    public SearchStepsScreen assertMainScreenOpen() {
        announcementText.shouldHave(text("Customize your Explore feed"));
        return this;
    }

    @Step("Click to Search Wikipedia")
    public SearchStepsScreen clickToSearch() {
        searchTab.click();
        return this;
    }

    @Step("Enter text {searchValue} in the search field")
    public SearchStepsScreen enterTextToSearchField(String searchValue) {
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchValue);
        return this;
    }

}
