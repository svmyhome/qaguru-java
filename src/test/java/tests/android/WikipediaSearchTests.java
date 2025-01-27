package tests.android;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import screens.android.OnBoardingScreen;
import screens.android.SearchStepsScreen;
import screens.android.SearchStepsScreenResult;
import tests.TestBase;

@Tag("android")
public class WikipediaSearchTests extends TestBase {

    OnBoardingScreen onBoardingScreen = new OnBoardingScreen();
    SearchStepsScreen searchScreen = new SearchStepsScreen();
    SearchStepsScreenResult resultsScreen = new SearchStepsScreenResult();

    @Test
    void testOnboardingScreenTransitionSuccess() {
        onBoardingScreen.assertTextExist("The Free Encyclopedia");
        onBoardingScreen.clickToForwardButton().assertTextExist("New ways to explore");
        onBoardingScreen.clickToForwardButton().assertTextExist("Reading lists with sync");
        onBoardingScreen.clickToForwardButton().assertTextExist("Data & Privacy");
        onBoardingScreen.clickToStartButton();
        searchScreen.assertMainScreenOpen();
    }
//
//    @Test
//    void testOnboardingScreenSkipSuccess() {
//        onBoardingScreen.assertTextExist("The Free Encyclopedia");
//        onBoardingScreen.clickToSkipButton();
//        searchScreen.assertMainScreenOpen();
//    }
//
//    @Test
//    void testSearchSuccess() {
//        String searchValue = "Appium";
//
//        onBoardingScreen.clickToSkipButton();
//
//        searchScreen.assertMainScreenOpen().clickToSearch().enterTextToSearchField(searchValue);
//        resultsScreen.assertTitleListIsNotEmpty();
//    }

}
