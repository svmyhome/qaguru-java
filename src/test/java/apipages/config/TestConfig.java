package apipages.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static apipages.constants.Constants.RunVariables.path;
import static apipages.constants.Constants.RunVariables.server;

public class TestConfig {

    @BeforeAll
    public static void setUP() {
        RestAssured.baseURI = server;
        RestAssured.basePath = path;
    }
}
