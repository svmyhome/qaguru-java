package screens.android;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class SearchStepsScreenResult {

    private final ElementsCollection titleList = $$(id("org.wikipedia.alpha:id/page_list_item_title"));


    @Step("Title count greater than 0")
    public SearchStepsScreenResult assertTitleListIsNotEmpty() {
        titleList.shouldHave(sizeGreaterThan(0));
        return this;
    }
}
