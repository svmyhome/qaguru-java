package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Project.isBrowserStackDevice;
import static drivers.GetMobileDriver.getMobileDriver;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = getMobileDriver();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();

    }


    @AfterEach
    public void Attachments() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);
        Attach.screenshotAs("picture");
        Attach.pageSource();
        closeWebDriver();
        if (isBrowserStackDevice) {
            Attach.addVideo(sessionId);
        }

    }
}
