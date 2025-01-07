package tests.owner.testing;

import configOwner.testing.WebDriverTestingConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class WebDriverTesting {

    @Test
    public void webDriverTest() {
        WebDriverTestingConfig testingConfig = ConfigFactory.create(WebDriverTestingConfig.class, System.getProperties());

        assertThat(testingConfig.baseUrl1()).isEqualTo("https://github.com");

    }

}
