package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/samsungS22Ultra.properties"
})
public interface BrowserStackAndroidConfig extends Config {
    @Key("android.app")
    @DefaultValue("bs://sample.app")
    String getApp();

    @Key("android.deviceName")
    @DefaultValue("Samsung Galaxy S22 Ultra")
    String getDeviceName();

    @Key("android.platformVersion")
    @DefaultValue("12.0")
    String getPlatformVersion();

    @Key("android.project")
    @DefaultValue("First Java Project")
    String getProjectName();

    @Key("android.build")
    @DefaultValue("browserstack-build-1")
    String getAndroidBuild();

    @Key("android.name")
    @DefaultValue("first_test")
    String getAndroidName();

}
