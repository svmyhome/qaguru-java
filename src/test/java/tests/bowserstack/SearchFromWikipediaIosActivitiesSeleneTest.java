package tests.bowserstack;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.appium.java_client.AppiumBy.accessibilityId;

@Tag("ios")
public class SearchFromWikipediaIosActivitiesSeleneTest extends TestBase {

    @Test
    public void WikipediaTest() throws IOException, InterruptedException {


//        WebElement textButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
//        textButton.click();

        $(accessibilityId("Text Button")).click();
        $(accessibilityId("Text Input")).sendKeys("hello@browserstack.com" + "\n");

//        WebElement textInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(accessibilityId("Text Input")));
//        textInput.sendKeys("hello@browserstack.com" + "\n");
//
//        Thread.sleep(5000);

        $(accessibilityId("Text Output")).shouldHave(text("hello@browserstack.com"));
//        WebElement textOutput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(accessibilityId("Text Output")));
//
//        assertEquals(textOutput.getText(), "hello@browserstack.com");
//        driver.quit();

        closeWebDriver();

    }


}
