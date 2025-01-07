package configOwner.testing;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:testing.properties"
})
public interface WebDriverTestingConfig extends Config {

    @Key("webdriver1.baseUrl1")
    String baseUrl1();

    @Key("webdriver1.browser1")
    String browser1();

}
