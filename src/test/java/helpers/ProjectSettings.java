package helpers;

import config.AuthConfig;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class ProjectSettings {

    public static final String deviceHost = System.getProperty("deviceHost", "real");
    public static final boolean isBrowserStackDevice = "browserstack".equals(deviceHost);

    public static final String platform = System.getProperty("platform", "android");
    public static final boolean isAndroid = platform.equals("android");

    public static class Credentials {
        static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        public static final String USER_NAME = System.getProperty("userBrowserStack", config.getUserName());
        public static final String PASSWORD = System.getProperty("passwordBrowserStack", config.getPassword());
    }

    public static class ProjectConfiguration {
        public static ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    }
}
