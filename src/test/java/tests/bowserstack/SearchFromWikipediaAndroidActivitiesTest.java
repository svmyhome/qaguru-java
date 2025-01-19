package tests.bowserstack;

import activities.ResultsActivities;
import activities.SearchActivities;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

@Tag("android")
public class SearchFromWikipediaAndroidActivitiesTest extends TestBase {
    SearchActivities searchActivities = new SearchActivities();
    ResultsActivities resultsActivities = new ResultsActivities();

    @Test
    public void WikipediaTest() {
        String searchValue = "Appium";

        searchActivities.clickToSearch().enterTextToSearchField(searchValue);
        resultsActivities.assertTitleListIsNotEmpty();
    }


    @Test
    public void FindSeleniumTest() {
        String searchValue = "Ubuntu";
        String expectedResult = "Linux distribution developed by Canonical";

        searchActivities.clickToSearch().enterTextToSearchField(searchValue);

        resultsActivities.assertTitleListIsNotEmpty().clickToListItemDescription(expectedResult);
    }
}
