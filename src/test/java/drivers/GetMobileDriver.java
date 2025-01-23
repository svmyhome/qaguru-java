package drivers;

import static config.Project.environment;

public class GetMobileDriver {
    public static String getMobileDriver() {
        if ("local".equals(environment)) {
            return LocalDriver.class.getName();
        } else if ("virtual".equals(environment)) {
            return VirtualDriver.class.getName();
        } else if ("remote".equals(environment)) {
            return BrowserStackDriver.class.getName();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android nor iOS.");
        }
    }
}
