package helpers;

public class EnvironmentHelper {

    // Platform config
    public static final String platform = System.getProperty("platform", "android");

    public static final boolean isIos = platform.equals("ios");

}
