package tests.bowserstack;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

@Tag("android")
public class SearchFromWikipediaAndroidOldTest {

    @Test
    public void WikipediaTest() throws MalformedURLException, InterruptedException {
//        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
//        caps.setCapability("browserstack.user", "petrpetr_SsBEec");
//        caps.setCapability("browserstack.key", "B3Ux6EuEBuEEWDGbLZeK");
//
//        // Set URL of the application under test
//        caps.setCapability("app", "bs://sample.app");
//
//        // Specify device and os_version for testing
//        caps.setCapability("device", "Google Pixel 3");
//        caps.setCapability("os_version", "9.0");
//
//        caps.setCapability("project", "First Java Project");
//        caps.setCapability("build", "browserstack-build-1");
//        caps.setCapability("name", "first_test");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("appium:app", "bs://sample.app");
        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        options.setCapability("appium:platformVersion", "12.0");
        options.setCapability("project", "First Java Project");
        options.setCapability("build", "browserstack-build-1");
        options.setCapability("name", "first_test");


        AndroidDriver driver = new AndroidDriver(
                new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", "petrpetr_SsBEec", "B3Ux6EuEBuEEWDGbLZeK")), options);

        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Search Wikipedia")));
        searchElement.click();
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("Appium");
        Thread.sleep(5000);
        List<WebElement> allProductsName = driver.findElements(AppiumBy.className(
                "android.widget.TextView"));
        assert (allProductsName.size() > 0);


        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();

    }


}
