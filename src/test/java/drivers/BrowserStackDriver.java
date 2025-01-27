package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.DeviceAndroidConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static helpers.BrowserstackHelper.getBrowserstackUrl;
import static helpers.Project.ProjectConfiguration.projectConfig;
import static helpers.Project.isAndroid;

public class BrowserStackDriver implements WebDriverProvider {
    DeviceAndroidConfig androidConfig;
    //    BrowserStackAndroidConfig androidConfig;
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
//        androidConfig = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());
        androidConfig = ConfigFactory.create(DeviceAndroidConfig.class, System.getProperties());
        androidOptions = new UiAutomator2Options();
        androidOptions.setCapability("appium:app", androidConfig.getApp());
        androidOptions.setCapability("appium:deviceName", androidConfig.getDeviceName());
        androidOptions.setCapability("appium:platformVersion", androidConfig.getPlatformVersion());
        androidOptions.setCapability("project", projectConfig.getProjectName());
        androidOptions.setCapability("build", projectConfig.getProjectName() + " Android");
        androidOptions.setCapability("name", projectConfig.getTestName() + " " + androidConfig.getDeviceName());

        return new AndroidDriver(getBrowserstackUrl(), androidOptions);
    }

}
