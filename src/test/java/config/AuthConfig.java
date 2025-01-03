package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/auth.properties",
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("userName")
    String userName();

    @Key("userPassword")
    String userPassword();
}
