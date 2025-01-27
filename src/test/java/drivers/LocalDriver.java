package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.LocalAndroidConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static config.Project.isAndroid;
import static drivers.GetMobileDriver.getMobileDevice;
import static helpers.LocalHelper.getAppPath;
import static helpers.LocalHelper.getLocalUrl;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriver implements WebDriverProvider {
    LocalAndroidConfig androidConfig;
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
        getMobileDevice("redmiNote4");

        androidConfig = ConfigFactory.create(LocalAndroidConfig.class, System.getProperties());
        androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName(ANDROID_UIAUTOMATOR2);
        androidOptions.setPlatformName(ANDROID);
        androidOptions.setPlatformVersion(androidConfig.getPlatformVersion());
        androidOptions.setUdid(androidConfig.getUdid());
        androidOptions.setApp(getAppPath());
        androidOptions.setAppPackage(androidConfig.getAppPackage());
        androidOptions.setAppActivity(androidConfig.getAppActivity());

        return new AndroidDriver(getLocalUrl(), androidOptions);
    }

}
