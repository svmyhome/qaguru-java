package api.reqres;

import apipages.config.TestConfig;
import apipages.models.create_user.CreateUserRequestBodyModel;
import apipages.models.create_user.CreateUserResponseBodyModel;
import apipages.models.list_users.ListUsersResponseBodyModel;
import apipages.models.list_users.SingleUserResponseBodyModel;
import apipages.models.login_user.FailLoginUserResponseBodyModel;
import apipages.models.login_user.LoginUserRequestBodyModel;
import apipages.models.login_user.LoginUserResponseBodyModel;
import apipages.models.login_user.UseNotFoundResponseBodyModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.*;


@Tag("API")
public class ReqResApiTest extends TestConfig {

    @Test
    public void getListUsersTest() {

        ListUsersResponseBodyModel response = step("Make response", () -> {
            return given(listUsersRequestSpecification).queryParam("page", 2)
                    .when().get(LIST_USERS)
                    .then().spec(listUsersResponseSpecification)
                    .extract().as(ListUsersResponseBodyModel.class);
        });

        step("Check response", () -> {
            assertEquals(2, response.getPage());
            assertEquals(2, response.getTotal_pages());
        });
    }

    @Test
    public void getSingleUserTest() {
        SingleUserResponseBodyModel response = step("Make response", () -> {
            return given(singleUserRequestSpecification)
                    .when().get(USERS + "2")
                    .then()
                    .spec(singleUserResponseSpecification)
                    .extract().as(SingleUserResponseBodyModel.class);
        });
        step("Check response", () -> {
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("Janet", response.getData().getFirst_name());
            assertEquals("Weaver", response.getData().getLast_name());
        });

    }

    @Test
    public void singleUserNotFoundTest() {

        UseNotFoundResponseBodyModel response = step("Make response", () -> {
            return given(notFoundUserRequestSpecification)
                    .when().get(USERS + "22")
                    .then().spec(notFoundUserResponseSpecification)
                    .extract().as(UseNotFoundResponseBodyModel.class);
        });
    }

    @Test
    public void createUserTest() throws Exception {
        CreateUserRequestBodyModel authData = new CreateUserRequestBodyModel("morpheus", "leader");

        CreateUserResponseBodyModel response = step("Make response", () -> {
            return given(createUserRequestSpecification).body(authData)
                    .when().post(USERS)
                    .then().spec(createUserResponseSpecification)
                    .extract().as(CreateUserResponseBodyModel.class);
        });

        step("Check response", () -> {
            assertEquals(authData.getName(), response.getName());
            assertEquals(authData.getJob(), response.getJob());
        });

    }

    @Test
    public void loginSuccessfulTest() throws Exception {
        LoginUserRequestBodyModel authData = new LoginUserRequestBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginUserResponseBodyModel response = step("Make response", () -> {
            return given(loginSuccessfulRequestSpecification)
                    .body(authData)
                    .when().post(LOGIN)
                    .then()
                    .spec(loginSuccessfulResponseSpecification)
                    .extract().as(LoginUserResponseBodyModel.class);
        });

        step("Check response", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });


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

        step("Check response", () -> {
            assertEquals("Missing password", response.getError());
        });

    }
}
