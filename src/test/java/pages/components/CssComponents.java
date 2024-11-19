package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;

public class CssComponents {

    public void checkElementColor(SelenideElement element, String cssElement, String color) {
        element.shouldHave((cssValue(cssElement, color)));
    }
}
