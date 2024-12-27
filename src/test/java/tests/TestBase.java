package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.browserSize = System.getProperty("browserSize", "1000x1900");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getProperty("remote");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

}
