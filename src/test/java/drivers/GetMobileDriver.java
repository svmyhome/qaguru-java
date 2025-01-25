package drivers;

import static config.Project.device;
import static config.Project.environment;

public class GetMobileDriver {
    public static String getMobileDriver() {
        if ("real".equals(environment)) {
            return LocalDriver.class.getName();
        } else if ("emulation".equals(environment)) {
            return VirtualDriver.class.getName();
        } else if ("remote".equals(environment)) {
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
