package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import configOwner.LocalWebDriverConfig;
import configOwner.RemoteWebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.Objects;

public class TestBase {
    static LocalWebDriverConfig localWebDriverConfig;
    static RemoteWebDriverConfig remoteWebDriverConfig;

    @BeforeAll
    public static void setUp() {
        String remote = System.getProperty("remoteStart");
        if (Objects.isNull(remote)) {
            localWebDriverConfig = ConfigFactory.create(LocalWebDriverConfig.class, System.getProperties());
            RestAssured.baseURI = localWebDriverConfig.getApiUrl();
            Configuration.baseUrl = localWebDriverConfig.getBaseUrl();
            Configuration.browser = localWebDriverConfig.getBrowserName();
            Configuration.browserVersion = localWebDriverConfig.getBrowserVersion();
            Configuration.browserSize = localWebDriverConfig.getBrowserSize();
            Configuration.pageLoadStrategy = "eager";
        } else {
            remoteWebDriverConfig = ConfigFactory.create(RemoteWebDriverConfig.class, System.getProperties());
            Configuration.remote = remoteWebDriverConfig.getRemoteUrl();
            RestAssured.baseURI = remoteWebDriverConfig.getApiUrl();
            Configuration.baseUrl = remoteWebDriverConfig.getBaseUrl();
            Configuration.browser = remoteWebDriverConfig.getBrowserName();
            Configuration.browserVersion = remoteWebDriverConfig.getBrowserVersion();
            Configuration.browserSize = remoteWebDriverConfig.getBrowserSize();
            Configuration.pageLoadStrategy = "eager";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        if (testInfo.getTags().contains("full")) {
            Attach.screenshotAs("Финальный скриншот");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }

}
