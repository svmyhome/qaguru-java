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
import java.net.MalformedURLException;
import java.net.URL;

import static config.Constants.Credentials.PASSWORD;
import static config.Constants.Credentials.USER_NAME;

public class BrowserStackDriver implements WebDriverProvider {
    BrowserStackAndroidConfig androidConfig;
    UiAutomator2Options androidOptions;
    BrowserStackIosConfig iosConfig;
    XCUITestOptions iosOptions;


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        String platformName = System.getProperty("platformName");
        if ("android".equals(platformName)) {
            return createAndroidDriver();
        } else if ("ios".equals(platformName)) {
            return createIosDriver();
        }
        return null; //TODO переделеть на сообщение
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
        androidOptions.setCapability("project", androidConfig.getProjectName());
        androidOptions.setCapability("build", androidConfig.getAndroidBuild());
        androidOptions.setCapability("name", androidConfig.getAndroidTestName());


        try {
            return new AndroidDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", USER_NAME, PASSWORD)), androidOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public IOSDriver createIosDriver() {
        String device = System.getProperty("device");
        if (device == null) {
            device = "iphoneXS";
            System.setProperty("device", device);
        }
        iosOptions = new XCUITestOptions();

        iosOptions.setCapability("appium:app", iosConfig.getApp());
        iosOptions.setCapability("appium:deviceName", iosConfig.getDeviceName());
        iosOptions.setCapability("appium:platformVersion", iosConfig.getPlatformVersion());
        iosOptions.setCapability("project", androidConfig.getProjectName());
        iosOptions.setCapability("build", androidConfig.getAndroidBuild());
        iosOptions.setCapability("name", androidConfig.getAndroidTestName());


        try {
            return new IOSDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", USER_NAME, PASSWORD)), iosOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
