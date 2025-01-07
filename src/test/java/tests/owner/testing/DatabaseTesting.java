package tests.owner.testing;

import configOwner.testing.DataBaseTestingConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DatabaseTesting {

    @Test
    public void apiTest() {
        DataBaseTestingConfig testingConfig = ConfigFactory.create(DataBaseTestingConfig.class, System.getProperties());

        assertThat(testingConfig.databaseUrl()).isEqualTo("postgres");

    }

}
