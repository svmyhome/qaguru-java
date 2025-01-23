package tests.android;

import activities.android.OnBoardingActivities;
import activities.android.ResultsActivities;
import activities.android.SearchActivities;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

@Tag("android")
public class WikipediaSearchTests extends TestBase {

    OnBoardingActivities onBoardingActivities = new OnBoardingActivities();
    SearchActivities searchActivities = new SearchActivities();
    ResultsActivities resultsActivities = new ResultsActivities();

    @Test
    void successfulSearchTest() {
        String searchValue = "Appium";

        onBoardingActivities.assertTextExist("The Free Encyclopedia");
        onBoardingActivities.clickToForwardButton().assertTextExist("New ways to explore");
        onBoardingActivities.clickToForwardButton().assertTextExist("Reading lists with sync");
        onBoardingActivities.clickToForwardButton().assertTextExist("Data & Privacy");
        onBoardingActivities.clickToStartButton();

        searchActivities.assertMainScreenOpen().clickToSearch().enterTextToSearchField(searchValue);

        resultsActivities.assertTitleListIsNotEmpty();
    }
}
