package apipages.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static apipages.constants.Constants.RunVariables.path;
import static apipages.constants.Constants.RunVariables.server;

public class TestConfig {

    protected ResponseSpecification responseSpecificationPost = new ResponseSpecBuilder()
            .expectHeader("Content-Type", "application/json; charset=utf-8")
            .expectStatusCode(201)
            .build();

    protected ResponseSpecification responseSpecificationDelete = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();

    protected ResponseSpecification responseSpecificationGet = new ResponseSpecBuilder()
            .expectHeader("Content-Type", "application/json; charset=utf-8")
            .expectStatusCode(200)
            .build();

    @BeforeAll
    public static void setUP() {
        RestAssured.baseURI = server;
        RestAssured.basePath = path;

        RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addCookie("TestCookies", "Json").build();

        RestAssured.requestSpecification = requestSpecificationJson;


    }
}
