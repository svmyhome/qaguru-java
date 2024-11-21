package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import pages.TextBoxPage;

import java.util.Locale;
import java.util.Map;

public class TestBase {
    protected RegistrationPage registrationPage;
    TextBoxPage textBoxPage;
    protected Faker fakerRu;
    protected Faker fakerEng;

    public TestBase() {
        registrationPage = new RegistrationPage();
        textBoxPage = new TextBoxPage();
        fakerRu = new Faker(new Locale("ru"));
        fakerEng = new Faker(new Locale("en-US"));
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1500x1200";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Финальный скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
