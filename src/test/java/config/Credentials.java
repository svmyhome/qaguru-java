package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/auth.properties"
})
public interface Credentials extends Config {

    @Key("userBrowserStack")
    String getLogin();

    @Key("passwordBrowserStack")
    String getPassword();
}
