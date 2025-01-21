package config;

import org.aeonbits.owner.ConfigFactory;

public class Constants {

    public static final String platform = System.getProperty("platform", "android");

    public static final boolean isIos = platform.equals("ios");
    public static final boolean isAndroid = platform.equals("android");

    public static class Credentials {
        static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        public static final String USER_NAME = System.getProperty("userBrowserStack", config.getUserName());
        public static final String PASSWORD = System.getProperty("passwordBrowserStack", config.getPassword());
    }

    public static class Project {
        static ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        public static final String PROJECT_NAME = System.getProperty("browserStack.project", projectConfig.getProjectName());
        public static final String BUILD_NAME = System.getProperty("browserStack.build", projectConfig.getBuildName());
        public static final String TEST_NAME = System.getProperty("browserStack.testName", projectConfig.getTestName());

    }
}
