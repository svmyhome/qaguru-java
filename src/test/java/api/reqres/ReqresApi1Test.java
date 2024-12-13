package api.reqres;

import apipages.config.TestConfig;
import apipages.models.lombok.LoginUserRequestBodyModel;
import apipages.models.lombok.LoginUserResponseBodyModel;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.LOGIN;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.loginSuccessfulRequestSpecification;
import static specs.LoginSpecs.loginSuccessfulResponseSpecification;

public class ReqresApi1Test extends TestConfig {

    //   @Test
//    public void getListUsersTest() {
//        given(listUsersRequestSpecification).queryParam("page", 2)
//                .when().get(LIST_USERS)
//                .then().body(matchesJsonSchemaInClasspath("JsonSchemas/ListUsersSchema.json"))
//                .spec(listUsersResponseSpecification);
//    }
//
//    @Test
//    public void getSingleUserTest() {
//        given(singleUserRequestSpecification)
//                .when().get(USERS + "2")
//                .then()
//                .spec(singleUserResponseSpecification)
//                .body("data.email", is("janet.weaver@reqres.in"))
//                .body("data.first_name", is("Janet"))
//                .body("data.last_name", is("Weaver"));
//    }
//
//    @Test
//    public void singleUserNotFoundTest() {
//        given(notFoundUserRequestSpecification)
//                .when().get(USERS + "22")
//                .then().spec(notFoundUserResponseSpecification);
//    }
//
//    @Test
//    public void createUserTest() throws Exception {
//        CreateUserBodyModelLombok authData = new CreateUserBodyModelLombok("morpheus", "leader");
//
//        given(createUserRequestSpecification).body(authData)
//                .when().post(USERS)
//                .then().spec(createUserResponseSpecification)
//                .body("name", is(authData.getName()))
//                .body("job", is(authData.getJob()));
//    }

    @Test
    public void loginSuccessfulTest() throws Exception {
        LoginUserRequestBodyModel authData = new LoginUserRequestBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginUserResponseBodyModel response = given(loginSuccessfulRequestSpecification)
                .body(authData)
                .when().post(LOGIN)
                .then()
                .spec(loginSuccessfulResponseSpecification)
                .extract().as(LoginUserResponseBodyModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

//    @Test
//    public void loginUnsuccessfulTest() throws Exception {
//
//        LoginUserRequestBodyModel loginUser = new LoginUserRequestBodyModel("eve.holt@reqres.in", null);
//
//        given(loginUnsuccessfulRequestSpecification).body(loginUser)
//                .when().post(LOGIN)
//                .then()
//                .spec(loginUnsuccessfulResponseSpecification)
//                .body("error", containsString("Missing password"))
//                .log().body().statusCode(400);
//    }
//
//
//    @Test
//    public void deleteUserTest() {
//        given(deleteUserRequestSpecification)
//                .when().delete(USERS + "2")
//                .then().spec(deleteUserResponseSpecification).log().body();
//    }

}
