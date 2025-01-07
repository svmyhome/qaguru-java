package tests.owner;


import configOwner.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthTest {

    @Test
    public void authTest() {
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

        assertThat(authConfig.userName()).isEqualTo("vindisel2");
        assertThat(authConfig.userPassword()).isEqualTo("Qaz2@wsx");
    }

    @Test
    public void authWithSecretFileTest() throws IOException {
//        String content = "username=secret-user\npassword=secret-pass";
//        Path secret = Paths.get("secret.properties");
//
//        Files.write(secret, content.getBytes(StandardCharsets.UTF_8));
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

        assertThat(config.userName()).isEqualTo("vindisel");
        assertThat(config.userPassword()).isEqualTo("Qaz2@wsx");

//        Files.delete(secret);
    }


}
