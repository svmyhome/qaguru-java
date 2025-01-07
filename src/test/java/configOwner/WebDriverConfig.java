package configOwner;

import org.aeonbits.owner.Config;

import java.net.URL;

public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    public String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    public Browsers getBrowser();

    @Key("remoteUrl")
    @DefaultValue("https://localhost:4444")
    public URL getRemoteUrl();
}
