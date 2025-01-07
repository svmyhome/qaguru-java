package tests.owner.testing;

import configOwner.testing.ApiTestingConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ApiTesting {

    @Test
    public void apiTest() {
        ApiTestingConfig testingConfig = ConfigFactory.create(ApiTestingConfig.class, System.getProperties());

        assertThat(testingConfig.apiUrl()).isEqualTo("https://api.com");

    }

}
