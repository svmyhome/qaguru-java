package tests.bowserstack;

import activities.ios.MainStepsScreen;
import activities.ios.ResultStepsScreenResult;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

@Tag("ios")
public class SearchFromUiElementsIosTest extends TestBase {
    MainStepsScreen mainActivities = new MainStepsScreen();
    ResultStepsScreenResult resultActivities = new ResultStepsScreenResult();

    @Test
    public void testSearchTextUI() {
        String text = "Hello world!";

        mainActivities.clickToTextButton();

        resultActivities.enterTextToInput(text).assertTextExist(text);

    }

}
