package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.VirtualAndroidConfig;
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

public class VirtualDriver implements WebDriverProvider {
    VirtualAndroidConfig androidConfig;
    UiAutomator2Options androidOptions;

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        if (isAndroid) {
            return createAndroidDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android nor iOS.");
        }
    }

    public AndroidDriver createAndroidDriver() {
        getMobileDevice("pixel4");

        androidConfig = ConfigFactory.create(VirtualAndroidConfig.class, System.getProperties());
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

}
