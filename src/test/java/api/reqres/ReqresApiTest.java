package api.reqres;

import apipages.config.TestConfig;
import apipages.models.jackson.CreateUser;
import apipages.models.jackson.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static apipages.constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ReqresApiTest extends TestConfig {

    @Test
    public void getListUsersTest() {
        given().queryParam("page", 2)
                .log().uri().log().params()
                .when().get(LIST_USERS)
                .then().body(matchesJsonSchemaInClasspath("JsonSchemas/ListUsersSchema.json"))
                .log().body().statusCode(200);
    }

    @Test
    public void getSingleUserTest() {
        given().log().uri()
                .when().get(USERS + "2")
                .then()
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .log().body().statusCode(200);
    }

    @Test
    public void singleUserNotFoundTest() {
        given().log().uri()
                .when().get(USERS + "22")
                .then().log().body().statusCode(404);
    }

    @Test
    public void createUserTest() throws Exception {
        CreateUser user = new CreateUser("morpheus", "leader");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(user);

        given().body(jsonBody).log().uri().log().body()
                .when().post(USERS)
                .then().spec(responseSpecificationPost)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .log().body();
    }

    @Test
    public void loginSuccessfulTest() throws Exception {
        LoginUser loginUser = new LoginUser("eve.holt@reqres.in", "cityslicka");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(loginUser);

        given().body(jsonBody).log().uri().log().body()
                .when().post(LOGIN)
                .then()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .log().body().statusCode(200);
    }

    @Test
    public void loginUnsuccessfulTest() throws Exception {

        LoginUser loginUser = new LoginUser("eve.holt@reqres.in");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(loginUser);


        given().body(jsonBody).log().uri().log().body()
                .when().post(LOGIN)
                .then()
                .body("error", containsString("Missing password"))
                .log().body().statusCode(400);
    }


    @Test
    public void deleteUserTest() {
        given().log().uri()
                .when().delete(USERS + "2")
                .then().spec(responseSpecificationDelete).log().body();
    }

}
