package api.reqres;

import apipages.config.TestConfig;
import apipages.models.lombok.CreateUserBodyModelLombok;
import apipages.models.lombok.CreateUserBodyResponseLombok;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.USERS;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresApiAllureStepSpecsTest extends TestConfig {


//    @Test
//    public void postCreateUserTest() {
//
//        LoginBodyModel authData = new LoginBodyModel("morpheus", "leader");
//
//        LoginBodyResponse response = step("Make request", () -> {
//            return given(loginRequestSpecification)
//                    .body(authData)
//                    .when().post(USERS)
//                    .then()
//                    .spec(loginResponseSpecification)
//                    .extract().as(LoginBodyResponse.class);
//        });
//
//        step("Check response", () -> {
//            assertEquals("morpheus", response.getName());
//            assertEquals("leader", response.getJob());
//        });
//    }

    @Test
    public void putUpdateUserTest() {

        CreateUserBodyModelLombok authData = new CreateUserBodyModelLombok("morpheus", "zion resident");

        CreateUserBodyResponseLombok response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .when().put(USERS)
                .then().log().status()
                .log().body()
                .statusCode(200).extract().as(CreateUserBodyResponseLombok.class);

        assertEquals("morpheus", response.getName());
        assertEquals("zion resident", response.getJob());

    }


}
