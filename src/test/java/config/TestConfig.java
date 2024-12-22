package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static constants.Constants.RunVariables.server;

public class TestConfig {


    @BeforeAll
    public static void setUP() {
        RestAssured.baseURI = server;

        Configuration.browserSize = "1000x1500";
        Configuration.baseUrl = "https://demoqa.com";
    }

}
