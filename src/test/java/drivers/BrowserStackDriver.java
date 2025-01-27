package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackAndroidConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static config.Project.ProjectConfiguration.*;
import static config.Project.device;
import static config.Project.isAndroid;
import static drivers.GetMobileDriver.getMobileDevice;
import static helpers.BrowserstackHelper.getBrowserstackUrl;

public class BrowserStackDriver implements WebDriverProvider {
    BrowserStackAndroidConfig androidConfig;
    UiAutomator2Options androidOptions;

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        if (isAndroid) {
            return createAndroidDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android");
        }
    }

    public AndroidDriver createAndroidDriver() {
        getMobileDevice("pixel6Pro");

        androidConfig = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());
        androidOptions = new UiAutomator2Options();
        androidOptions.setCapability("appium:app", androidConfig.getApp());
        androidOptions.setCapability("appium:deviceName", androidConfig.getDeviceName());
        androidOptions.setCapability("appium:platformVersion", androidConfig.getPlatformVersion());
        androidOptions.setCapability("project", PROJECT_NAME);
        androidOptions.setCapability("build", BUILD_NAME + " Android");
        androidOptions.setCapability("name", TEST_NAME + " " + device);

        return new AndroidDriver(getBrowserstackUrl(), androidOptions);
    }

}
