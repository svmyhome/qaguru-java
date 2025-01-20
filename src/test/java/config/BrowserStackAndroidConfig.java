package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/android/${device}.properties",
        "classpath:config/android/onePlus9.properties",
        "classpath:config/android/samsungS22Ultra.properties"
})
public interface BrowserStackAndroidConfig extends Config {
    @Key("android.app")
    String getApp();

    @Key("android.deviceName")
    String getDeviceName();

    @Key("android.platformVersion")
    String getPlatformVersion();
}
