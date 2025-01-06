package tests.owner;

import configSelenium.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverTest {

    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = new WebDriverProvider().get();
    }

    @Tag("TEST_CONFIG")
    @Test
    public void testGithub() {
        String getTitle = driver.getTitle();
        assertEquals("GitHub · Build and ship software on a single, collaborative platform · GitHub", getTitle);
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }

}
