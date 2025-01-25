package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/emulation/android/${device}.properties",
        "classpath:config/emulation/android/pixel4.properties"
})
public interface VirtualAndroidConfig extends Config {
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
