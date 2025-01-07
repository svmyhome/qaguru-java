package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AuthConfig;
import config.LocalWebDriverConfig;
import config.RemoteWebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static config.WebDriverConfig.isRemoteStart;

public class TestBase {
    static AuthConfig authConfig = ConfigFactory
            .create(AuthConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {
//        boolean isRemoteStart = "true".equals(System.getProperty("remoteStart"));


        if (isRemoteStart()) {
            RemoteWebDriverConfig remoteWebDriverConfig = ConfigFactory
                    .create(RemoteWebDriverConfig.class, System.getProperties());
            Configuration.remote = "https://" + authConfig.selenoidUser() + ":" + authConfig.selenoindPassword() + remoteWebDriverConfig.getRemoteUrl();
            RestAssured.baseURI = remoteWebDriverConfig.getApiUrl();
            Configuration.baseUrl = remoteWebDriverConfig.getBaseUrl();
            Configuration.browser = remoteWebDriverConfig.getBrowserName();
            Configuration.browserVersion = remoteWebDriverConfig.getBrowserVersion();
            Configuration.browserSize = remoteWebDriverConfig.getBrowserSize();
            Configuration.pageLoadStrategy = remoteWebDriverConfig.getLoadStrategy();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        } else {
            LocalWebDriverConfig localWebDriverConfig = ConfigFactory
                    .create(LocalWebDriverConfig.class, System.getProperties());
            RestAssured.baseURI = localWebDriverConfig.getApiUrl();
            Configuration.baseUrl = localWebDriverConfig.getBaseUrl();
            Configuration.browser = localWebDriverConfig.getBrowserName();
            Configuration.browserVersion = localWebDriverConfig.getBrowserVersion();
            Configuration.browserSize = localWebDriverConfig.getBrowserSize();
            Configuration.pageLoadStrategy = localWebDriverConfig.getLoadStrategy();
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
