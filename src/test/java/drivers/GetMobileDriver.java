package drivers;

import static config.Project.device;
import static config.Project.deviceHost;

public class GetMobileDriver {
    public static String getMobileDriver() {
        if ("real".equals(deviceHost)) {
            return LocalDriver.class.getName();
        } else if ("emulation".equals(deviceHost)) {
            return VirtualDriver.class.getName();
        } else if ("browserstack".equals(deviceHost)) {
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
