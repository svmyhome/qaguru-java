package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/local/android/${device}.properties",
        "classpath:config/local/android/redmiNote4.properties"
})
public interface LocalAndroidConfig extends Config {
    @Key("android.app")
    String getApp();

    @Key("android.udid")
    String getUdid();

    @Key("android.platformVersion")
    String getPlatformVersion();

    @Key("android.appPackage")
    String getAppPackage();

    @Key("android.appActivity")
    String getAppActivity();
}
