package configOwner.testing;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:testing.properties"
})
public interface DataBaseTestingConfig extends Config {

    @Key("service.database.url")
    String databaseUrl();

    @Key("service.database.username")
    String databaseUsername();

    @Key("service.database.password")
    String databasePassword();

}

