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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static config.Constants.Project.*;
import static config.Constants.isAndroid;
import static config.Constants.isIos;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class LocalDriver implements WebDriverProvider {
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
//        String device = System.getProperty("device");
//        if (device == null) {
//            device = "pixel6Pro";
//            System.setProperty("device", device);
//        }
        androidConfig = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());

        androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName(ANDROID_UIAUTOMATOR2);
        androidOptions.setPlatformName(ANDROID);
        androidOptions.setPlatformVersion("7.0");
//        androidOptions.setDeviceName("Pixel_3a_API_34_extension_level_7_arm64");
        androidOptions.setUdid("a7d39a720604");
        androidOptions.setApp(getAppPath());
        androidOptions.setAppPackage("org.wikipedia.alpha");
        androidOptions.setAppActivity("org.wikipedia.main.MainActivity");

        return new AndroidDriver(getLocalUrl(), androidOptions);
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

        return new IOSDriver(getLocalUrl(), iosOptions);
    }

    public static URL getLocalUrl() {
        try {
            return new URL("http://192.168.31.143:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    private String getAppPath() {
        String appVersion = "app-alpha-universal-release.apk";
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia" +
                "/releases/download/latest/" + appVersion;
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }

}
