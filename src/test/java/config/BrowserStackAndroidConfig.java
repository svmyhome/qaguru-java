package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${device}.properties",
        "classpath:config/onePlus9.properties",
        "classpath:config/samsungS22Ultra.properties"
})
public interface BrowserStackAndroidConfig extends Config {
    @Key("android.app")
    String getApp();

    @Key("android.deviceName")
    String getDeviceName();

    @Key("android.platformVersion")
    String getPlatformVersion();

    @Key("android.project")
    String getProjectName();

    @Key("android.build")
    String getAndroidBuild();

    @Key("android.testName")
    String getAndroidTestName();

}
