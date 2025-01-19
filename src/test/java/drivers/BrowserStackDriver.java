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

import static config.Constants.Credentials.PASSWORD;
import static config.Constants.Credentials.USER_NAME;

public class BrowserStackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        String device = System.getProperty("device");
        if (device == null) {
            device = "pixel6Pro";
            System.setProperty("device", device);
        }
        BrowserStackAndroidConfig androidConfig = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());

        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("appium:app", androidConfig.getApp());
        options.setCapability("appium:deviceName", androidConfig.getDeviceName());
        options.setCapability("appium:platformVersion", androidConfig.getPlatformVersion());
        options.setCapability("project", androidConfig.getProjectName());
        options.setCapability("build", androidConfig.getAndroidBuild());
        options.setCapability("name", androidConfig.getAndroidTestName());


        try {
            return new AndroidDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", USER_NAME, PASSWORD)), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
