package activities.android;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class OnBoardingActivities {
    private final SelenideElement textView = $(id("org.wikipedia.alpha:id/primaryTextView")),
            forwardButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            doneButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));


    @Step("Enter text {text} in the input")
    public OnBoardingActivities assertTextExist(String text) {
        textView.shouldHave(text(text));
        return this;
    }


    @Step("Click to Continue")
    public OnBoardingActivities clickToForwardButton() {
        forwardButton.click();
        return this;
    }

    @Step("Click to Get started")
    public OnBoardingActivities clickToStartButton() {
        doneButton.click();
        return this;
    }
}
