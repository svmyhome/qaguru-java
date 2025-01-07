package configOwner;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties"
})
public interface LocalWebDriverConfig extends Config {

    @Config.Key("webdriver.baseUrl")
    @Config.DefaultValue("https://demoqa.com")
    public String getBaseUrl();

    @Config.Key("service.apiUrl")
    @Config.DefaultValue("https://demoqa.com")
    public String getApiUrl();

    @Config.Key("browser.name")
    @Config.DefaultValue("chrome")
    public String getBrowserName();

    @Config.Key("browser.version")
    @Config.DefaultValue("100.0")
    public String getBrowserVersion();

    @Config.Key("browser.size")
    @Config.DefaultValue("1000x1900")
    public String getBrowserSize();
}