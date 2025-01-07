package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import configOwner.AuthConfig;
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

public class TestBase {
    static LocalWebDriverConfig localWebDriverConfig = ConfigFactory
            .create(LocalWebDriverConfig.class, System.getProperties());
    static RemoteWebDriverConfig remoteWebDriverConfig = ConfigFactory
            .create(RemoteWebDriverConfig.class, System.getProperties());
    static AuthConfig authConfig = ConfigFactory
            .create(AuthConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {
        boolean isRemoteStart = "true".equals(System.getProperty("remoteStart"));
        Configuration.pageLoadStrategy = "eager";
        if (isRemoteStart) {
            Configuration.remote = "https://" + authConfig.selenoidUser() + ":" + authConfig.selenoindPassword() + remoteWebDriverConfig.getRemoteUrl();
            RestAssured.baseURI = remoteWebDriverConfig.getApiUrl();
            Configuration.baseUrl = remoteWebDriverConfig.getBaseUrl();
            Configuration.browser = remoteWebDriverConfig.getBrowserName();
            Configuration.browserVersion = remoteWebDriverConfig.getBrowserVersion();
            Configuration.browserSize = remoteWebDriverConfig.getBrowserSize();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        } else {
            RestAssured.baseURI = localWebDriverConfig.getApiUrl();
            Configuration.baseUrl = localWebDriverConfig.getBaseUrl();
            Configuration.browser = localWebDriverConfig.getBrowserName();
            Configuration.browserVersion = localWebDriverConfig.getBrowserVersion();
            Configuration.browserSize = localWebDriverConfig.getBrowserSize();
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
