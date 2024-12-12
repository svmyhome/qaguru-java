package api.reqres;

import apipages.config.TestConfig;
import apipages.models.lombok.LoginBodyModelLombok;
import apipages.models.lombok.LoginBodyResponseLombok;
import apipages.models.pojo.LoginBodyModel;
import apipages.models.pojo.LoginBodyResponse;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.USERS;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.loginRequestSpecification;
import static specs.LoginSpecs.loginResponseSpecification;

public class ReqresApiAllureStepSpecsTest extends TestConfig {


    @Test
    public void postCreateUserTest() {

        LoginBodyModel authData = new LoginBodyModel("morpheus", "leader");

        LoginBodyResponse response = step("Make request", () -> {
            return given(loginRequestSpecification)
                    .body(authData)
                    .when().post(USERS)
                    .then()
                    .spec(loginResponseSpecification)
                    .extract().as(LoginBodyResponse.class);
        });

        step("Check response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    @Test
    public void putUpdateUserTest() {

        LoginBodyModelLombok authData = new LoginBodyModelLombok("morpheus", "zion resident");

        LoginBodyResponseLombok response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .when().put(USERS)
                .then().log().status()
                .log().body()
                .statusCode(200).extract().as(LoginBodyResponseLombok.class);

        assertEquals("morpheus", response.getName());
        assertEquals("zion resident", response.getJob());

    }


}
