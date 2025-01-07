package config;

public class WebDriverConfig {

    public static Boolean isRemoteStart() {
        return "true".equals(System.getProperty("remoteStart"));
    }
}
