package tests.owner;

import configOwner.TypeConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TypeTest {

    @Test
    public void testInteger() {
        System.setProperty("integer", "10");
        TypeConfig typeConfig = ConfigFactory.create(TypeConfig.class, System.getProperties());

        assertThat(typeConfig.getInteger()).isEqualTo(10);
    }
}
