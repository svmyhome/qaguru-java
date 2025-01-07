package configOwner;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties"
})
public interface LocalWebDriverConfig extends Config {

    @Config.Key("webdriver.baseUrl")
    @Config.DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Config.Key("service.apiUrl")
    @Config.DefaultValue("https://demoqa.com")
    String getApiUrl();

    @Config.Key("browser.name")
    @Config.DefaultValue("chrome")
    String getBrowserName();

    @Config.Key("browser.version")
    @Config.DefaultValue("100.0")
    String getBrowserVersion();

    @Config.Key("browser.size")
    @Config.DefaultValue("1000x1300")
    String getBrowserSize();
}