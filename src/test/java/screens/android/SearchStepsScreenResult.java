package screens.android;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class SearchStepsScreenResult {

    private final SelenideElement listItemDescription = $(id("org.wikipedia.alpha:id/page_list_item_description"));
    private final ElementsCollection titleList = $$(id("org.wikipedia.alpha:id/page_list_item_title"));


    @Step("Title count greater than 0")
    public SearchStepsScreenResult assertTitleListIsNotEmpty() {
        titleList.shouldHave(sizeGreaterThan(0));
        return this;
    }


    @Step("Click to link")
    public SearchStepsScreenResult clickToListItemDescription(String expectedResult) {
        listItemDescription.shouldHave(text(expectedResult)).click();
        return this;
    }
}
