package tests.bowserstack;

import activities.android.SearchStepsScreen;
import activities.android.SearchStepsScreenResult;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

@Tag("android")
public class SearchFromWikipediaAndroidTest extends TestBase {
    SearchStepsScreen searchActivities = new SearchStepsScreen();
    SearchStepsScreenResult resultsActivities = new SearchStepsScreenResult();

    @Test
    public void testSearchWikipedia() {
        String searchValue = "Appium";

        searchActivities.clickToSearch().enterTextToSearchField(searchValue);
        resultsActivities.assertTitleListIsNotEmpty();
    }


    @Test
    public void testSearchSelenium() {
        String searchValue = "Ubuntu";
        String expectedResult = "Linux distribution developed by Canonical";

        searchActivities.clickToSearch().enterTextToSearchField(searchValue);

        resultsActivities.assertTitleListIsNotEmpty().clickToListItemDescription(expectedResult);
    }
}
