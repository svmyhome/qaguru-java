package activities.ios;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainActivities {
    private final SelenideElement textButton = $(accessibilityId("Text Button"));

    @Step("Click to textButton")
    public MainActivities clickToTextButton() {
        textButton.click();
        return this;
    }
}
