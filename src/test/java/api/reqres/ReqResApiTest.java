package api.reqres;

import apipages.config.TestConfig;
import apipages.models.createuser.CreateUserRequestBodyModel;
import apipages.models.createuser.CreateUserResponseBodyModel;
import apipages.models.listusers.ListUsersResponseBodyModel;
import apipages.models.listusers.SingleUserResponseBodyModel;
import apipages.models.loginuser.FailLoginUserResponseBodyModel;
import apipages.models.loginuser.LoginUserRequestBodyModel;
import apipages.models.loginuser.LoginUserResponseBodyModel;
import apipages.models.loginuser.UseNotFoundResponseBodyModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.*;
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
        UseNotFoundResponseBodyModel response = step("Make response", () -> given(notFoundUserRequestSpecification)
                .when().get(USERS + "22")
                .then().spec(notFoundUserResponseSpecification)
                .extract().as(UseNotFoundResponseBodyModel.class));
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
    public void loginSuccessfulTest() throws Exception {
        LoginUserRequestBodyModel authData = new LoginUserRequestBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginUserResponseBodyModel response = step("Make response", () -> given()
                .spec(loginSuccessfulRequestSpecification)
                .body(authData)
                .when().post(LOGIN)
                .then()
                .spec(loginSuccessfulResponseSpecification)
                .extract().as(LoginUserResponseBodyModel.class));

        step("Check response", () -> assertFalse(response.getToken().isEmpty()));
    }

    @Test
    public void loginUnsuccessfulTest() throws Exception {

        LoginUserRequestBodyModel authData = new LoginUserRequestBodyModel("eve.holt@reqres.in", null);

        FailLoginUserResponseBodyModel response = step("Make response", () -> {
            return given(loginUnsuccessfulRequestSpecification).body(authData)
                    .when().post(LOGIN)
                    .then()
                    .spec(loginUnsuccessfulResponseSpecification)
                    .extract().as(FailLoginUserResponseBodyModel.class);
        });

        step("Check response", () -> assertEquals("Missing password", response.getError()));
    }
}
