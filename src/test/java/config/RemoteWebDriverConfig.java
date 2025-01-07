package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/remote.properties"
})
public interface RemoteWebDriverConfig extends Config {

    @Key("remote.url")
    String getRemoteUrl();

    @Key("webdriver.baseUrl")
    String getBaseUrl();

    @Key("service.apiUrl")
    String getApiUrl();

    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    String getBrowserVersion();

    @Key("browser.size")
    String getBrowserSize();

    @Config.Key("load.strategy")
    @Config.DefaultValue("eager")
    String getLoadStrategy();
}