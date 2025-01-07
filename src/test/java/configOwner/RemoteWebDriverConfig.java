package configOwner;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:remote.properties"
})
public interface RemoteWebDriverConfig extends Config {

    @Key("remote.url")
    public String getRemoteUrl();

    @Key("webdriver.baseUrl")
    public String getBaseUrl();

    @Key("service.apiUrl")
    public String getApiUrl();

    @Key("browser.name")
    public String getBrowserName();

    @Key("browser.version")
    public String getBrowserVersion();

    @Key("browser.size")
    public String getBrowserSize();
}