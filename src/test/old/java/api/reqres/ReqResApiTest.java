package api.reqres;

import config.TestConfig;
import models.createuser.CreateUserRequestBodyModel;
import models.createuser.CreateUserResponseBodyModel;
import models.listusers.ListUsersResponseBodyModel;
import models.listusers.SingleUserResponseBodyModel;
import models.loginuser.FailLoginUserResponseBodyModel;
import models.loginuser.LoginUserRequestBodyModel;
import models.loginuser.LoginUserResponseBodyModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.Constants.Actions.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static specs.LoginSpecs.*;


@Tag("API")
public class ReqResApiTest extends TestConfig {

    @Test
    public void getListUsersTest() {
        ListUsersResponseBodyModel response = step("Make response", () -> given(listUsersRequestSpecification)
                .queryParam("page", 2)
                .when().get(LIST_USERS)
                .then().spec(listUsersResponseSpecification)
                .extract().as(ListUsersResponseBodyModel.class));

        step("Check response", () -> {
            assertEquals(2, response.getPage());
            assertEquals(2, response.getTotalPages());
        });
    }

    @Test
    public void getSingleUserTest() {
        SingleUserResponseBodyModel response = step("Make response", () -> given(singleUserRequestSpecification)
                .when().get(USERS + "2")
                .then()
                .spec(singleUserResponseSpecification)
                .extract().as(SingleUserResponseBodyModel.class));

        step("Check response", () -> {
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("Janet", response.getData().getFirstName());
            assertEquals("Weaver", response.getData().getLastName());
        });
    }

    @Test
    public void singleUserNotFoundTest() {
        step("Make response", () -> given(notFoundUserRequestSpecification)
                .when().get(USERS + "22")
                .then().spec(notFoundUserResponseSpecification));
    }

    @Test
    public void createUserTest() {
        CreateUserRequestBodyModel authData = new CreateUserRequestBodyModel("morpheus", "leader");

        CreateUserResponseBodyModel response = step("Make response", () -> given(createUserRequestSpecification)
                .body(authData)
                .when().post(USERS)
                .then().spec(createUserResponseSpecification)
                .extract().as(CreateUserResponseBodyModel.class));

        step("Check response", () -> {
            assertEquals(authData.getName(), response.getName());
            assertEquals(authData.getJob(), response.getJob());
        });
    }

    @Test
    public void loginSuccessfulTest() {
        LoginUserRequestBodyModel authData = new LoginUserRequestBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginUserResponseBodyModel response = step("Make response", () -> given(loginSuccessfulRequestSpecification)
                .body(authData)
                .when().post(LOGIN)
                .then()
                .spec(loginSuccessfulResponseSpecification)
                .extract().as(LoginUserResponseBodyModel.class));

        step("Check response", () -> assertFalse(response.getToken().isEmpty()));
    }

    @Test
    public void loginUnsuccessfulTest() {

        LoginUserRequestBodyModel authData = new LoginUserRequestBodyModel("eve.holt@reqres.in", null);

        FailLoginUserResponseBodyModel response = step("Make response", () -> given(loginUnsuccessfulRequestSpecification).body(authData)
                .when().post(LOGIN)
                .then()
                .spec(loginUnsuccessfulResponseSpecification)
                .extract().as(FailLoginUserResponseBodyModel.class));

        step("Check response", () -> assertEquals("Missing password", response.getError()));
    }
}
