package tests.owner;

import configOwner.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MobileTest {

    @Test
    public void mobileTest() {
        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(mobileConfig.getDeviceName()).isEqualTo("iPhone 13");
        assertThat(mobileConfig.getPlatformVersion()).isEqualTo("13");
        assertThat(mobileConfig.getPlatformName()).isEqualTo("IOS");
    }

    @Test
    public void mobileOverridesTest() {
        System.setProperty("platform.version", "10");
        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(mobileConfig.getPlatformVersion()).isEqualTo("10");
    }

    @Test
    public void mobileIosTest() {
        System.setProperty("device", "iphon-10");
        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(mobileConfig.getDeviceName()).isEqualTo("iPhone 10");
        assertThat(mobileConfig.getPlatformVersion()).isEqualTo("10");
        assertThat(mobileConfig.getPlatformName()).isEqualTo("IOS");
    }

    @Test
    public void mobileAndroidTest() {
        System.setProperty("device", "google-pixel");
        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(mobileConfig.getDeviceName()).isEqualTo("Google Pixel");
        assertThat(mobileConfig.getPlatformVersion()).isEqualTo("24");
        assertThat(mobileConfig.getPlatformName()).isEqualTo("Android");
    }

}
