package activities.ios;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainStepsScreen {
    private final SelenideElement textButton = $(accessibilityId("Text Button"));

    @Step("Click to textButton")
    public MainStepsScreen clickToTextButton() {
        textButton.click();
        return this;
    }
}
