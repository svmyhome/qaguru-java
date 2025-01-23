package screens.ios;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class ResultStepsScreenResult {
    private final SelenideElement textInput = $(accessibilityId("Text Input")),
            textOutput = $(accessibilityId("Text Output"));

    @Step("Enter text {text} in the input")
    public ResultStepsScreenResult enterTextToInput(String text) {
        textInput.sendKeys(text + "\n");

        return this;
    }

    @Step("Enter text {text} in the input")
    public ResultStepsScreenResult assertTextExist(String text) {
        textOutput.shouldHave(Condition.text(text));

        return this;
    }
}
