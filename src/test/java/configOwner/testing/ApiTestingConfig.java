package configOwner.testing;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:testing.properties"
})
public interface ApiTestingConfig extends Config {

    @Key("service.api.url")
    String apiUrl();

    @Key("service.api.username")
    String apiUsername();

    @Key("service.api.password")
    String apiPassword();

}
