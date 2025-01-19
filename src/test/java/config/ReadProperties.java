package config;

import org.aeonbits.owner.ConfigFactory;

public class ReadProperties {

    public static void getCredentials() {
        Credentials credentials = ConfigFactory.create(Credentials.class, System.getProperties());
        public static final USER_NAME =credentials.getLogin();
        public static final USER_PASSWORD =credentials.getPassword();
    }
}
