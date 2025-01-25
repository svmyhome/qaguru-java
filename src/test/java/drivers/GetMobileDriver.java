package drivers;

import static config.Project.*;

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

    public static void getMobileDevice(String deviceName) {
        if (device == null) {
            device = deviceName;
            System.setProperty("device", device);
        }
    }

}
