package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/ios/${device}.properties",
        "classpath:config/ios/iphone12.properties"
})
public interface BrowserStackIosConfig extends Config {
    @Key("ios.app")
    String getApp();

    @Key("ios.deviceName")
    String getDeviceName();

    @Key("ios.platformVersion")
    String getPlatformVersion();
}
