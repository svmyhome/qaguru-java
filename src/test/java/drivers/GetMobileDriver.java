package drivers;

import static helpers.Project.*;

public class GetMobileDriver {
    public static String getMobileDriver() {
        if (isRealDevice) {
            return LocalDriver.class.getName();
        } else if (isEmulationDevice) {
            return VirtualDriver.class.getName();
        } else if (isBrowserStackDevice) {
            return BrowserStackDriver.class.getName();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android nor iOS.");
        }
    }

}
