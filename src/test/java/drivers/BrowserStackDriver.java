package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackAndroidConfig;
import config.BrowserStackIosConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static config.Config.Project.*;
import static config.Config.isAndroid;
import static config.Config.isIos;
import static helpers.BrowserstackHelper.getBrowserstackUrl;

public class BrowserStackDriver implements WebDriverProvider {
    BrowserStackAndroidConfig androidConfig;
    UiAutomator2Options androidOptions;
    BrowserStackIosConfig iosConfig;
    XCUITestOptions iosOptions;


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        if (isAndroid) {
            return createAndroidDriver();
        } else if (isIos) {
            return createIosDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android nor iOS.");
        }
    }


    public AndroidDriver createAndroidDriver() {
        String device = System.getProperty("device");
        if (device == null) {
            device = "pixel6Pro";
            System.setProperty("device", device);
        }
        androidConfig = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());

        androidOptions = new UiAutomator2Options();
        androidOptions.setCapability("appium:app", androidConfig.getApp());
        androidOptions.setCapability("appium:deviceName", androidConfig.getDeviceName());
        androidOptions.setCapability("appium:platformVersion", androidConfig.getPlatformVersion());
        androidOptions.setCapability("project", PROJECT_NAME);
        androidOptions.setCapability("build", BUILD_NAME + " Android");
        androidOptions.setCapability("name", TEST_NAME + " " + device);

        return new AndroidDriver(
                getBrowserstackUrl(), androidOptions);
    }

    public IOSDriver createIosDriver() {
        String device = System.getProperty("device");
        if (device == null) {
            device = "iphoneXS";
            System.setProperty("device", device);
        }

        iosConfig = ConfigFactory.create(BrowserStackIosConfig.class, System.getProperties());

        iosOptions = new XCUITestOptions();
        iosOptions.setCapability("appium:app", iosConfig.getApp());
        iosOptions.setCapability("appium:deviceName", iosConfig.getDeviceName());
        iosOptions.setCapability("appium:platformVersion", iosConfig.getPlatformVersion());
        iosOptions.setCapability("project", PROJECT_NAME);
        iosOptions.setCapability("build", BUILD_NAME + " Ios");
        iosOptions.setCapability("name", TEST_NAME + " " + device);

        return new IOSDriver(getBrowserstackUrl(), iosOptions);
    }
}
