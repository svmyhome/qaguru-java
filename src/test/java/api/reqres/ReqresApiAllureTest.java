package api.reqres;

import apipages.config.TestConfig;
import apipages.models.lombok.LoginBodyModelLombok;
import apipages.models.lombok.LoginBodyResponseLombok;
import apipages.models.pojo.LoginBodyModel;
import apipages.models.pojo.LoginBodyResponse;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.USERS;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresApiAllureTest extends TestConfig {


    @Test
    public void postCreateUserTest() {

        LoginBodyModel authData = new LoginBodyModel("morpheus", "leader");

        LoginBodyResponse response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .when().post(USERS)
                .then().log().body().extract().as(LoginBodyResponse.class);
        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());

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
