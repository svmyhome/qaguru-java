package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackAndroidConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        BrowserStackAndroidConfig config = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("appium:app", "bs://sample.app");
        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        options.setCapability("appium:platformVersion", "12.0");
        options.setCapability("project", "First Java Project");
        options.setCapability("build", "browserstack-build-1");
        options.setCapability("name", "first_test");


        try {
            return new AndroidDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", "petrpetr_SsBEec", "B3Ux6EuEBuEEWDGbLZeK")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
