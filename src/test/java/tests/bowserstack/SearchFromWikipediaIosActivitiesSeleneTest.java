package tests.bowserstack;

import activities.ios.MainActivities;
import activities.ios.ResultActivities;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

@Tag("ios")
public class SearchFromWikipediaIosActivitiesSeleneTest extends TestBase {
    MainActivities mainActivities = new MainActivities();
    ResultActivities resultActivities = new ResultActivities();

    @Test
    public void UITest() {
        String text = "Hello world!";

        mainActivities.clickToTextButton();

        resultActivities.enterTextToInput(text).assertTextExist(text);

    }

}
