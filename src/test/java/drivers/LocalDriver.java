package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.LocalAndroidConfig;
import config.LocalIosConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static config.Project.ProjectConfiguration.*;
import static config.Project.*;
import static drivers.GetMobileDriver.getMobileDevice;
import static helpers.LocalHelper.getAppPath;
import static helpers.LocalHelper.getLocalUrl;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriver implements WebDriverProvider {
    LocalAndroidConfig androidConfig;
    UiAutomator2Options androidOptions;
    LocalIosConfig iosConfig;
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
        getMobileDevice("redmiNote4");

        androidConfig = ConfigFactory.create(LocalAndroidConfig.class, System.getProperties());
        androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName(ANDROID_UIAUTOMATOR2);
        androidOptions.setPlatformName(ANDROID);
        androidOptions.setPlatformVersion(androidConfig.getPlatformVersion());
//        androidOptions.setDeviceName("Pixel_3a_API_34_extension_level_7_arm64");
        androidOptions.setUdid(androidConfig.getUdid());
        androidOptions.setApp(getAppPath());
        androidOptions.setAppPackage(androidConfig.getAppPackage());
        androidOptions.setAppActivity(androidConfig.getAppActivity());

        return new AndroidDriver(getLocalUrl(), androidOptions);
    }

    public IOSDriver createIosDriver() {
        getMobileDevice("iphoneXS");

        iosConfig = ConfigFactory.create(LocalIosConfig.class, System.getProperties());
        iosOptions = new XCUITestOptions();
        iosOptions.setCapability("appium:app", iosConfig.getApp());
        iosOptions.setCapability("appium:deviceName", iosConfig.getDeviceName());
        iosOptions.setCapability("appium:platformVersion", iosConfig.getPlatformVersion());
        iosOptions.setCapability("project", PROJECT_NAME);
        iosOptions.setCapability("build", BUILD_NAME + " Ios");
        iosOptions.setCapability("name", TEST_NAME + " " + device);

        return new IOSDriver(getLocalUrl(), iosOptions);
    }


}
