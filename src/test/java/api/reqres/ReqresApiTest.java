package api.reqres;

import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyModelLombok;
import models.lombok.LoginBodyResponseLombok;
import models.pojo.LoginBodyModel;
import models.pojo.LoginBodyResponse;
import org.junit.jupiter.api.Test;

import static constants.Constants.Actions.USERS;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresApiTest extends TestConfig {


    @Test
    public void postCreateUserTest() {

        LoginBodyModel authData = new LoginBodyModel("morpheus", "leader");

        LoginBodyResponse response = given()
                .filter(new AllureRestAssured())
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
                .filter(new AllureRestAssured())
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
