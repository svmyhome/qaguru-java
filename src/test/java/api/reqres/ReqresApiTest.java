package api.reqres;

import apipages.config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static apipages.constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ReqresApiTest extends TestConfig {

    @Test
    public void firstTest() {
        given()
                .when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200);
    }

    @Test
    public void complexAllLogsTest() {
        given().log().all()
                .when().get(USERS + "2")
                .then().log().all().statusCode(200);
    }

    @Test
    public void complexIfFailLogsTest() {
        given().log().ifValidationFails()
                .when().get(USERS + "2")
                .then().log().ifValidationFails().statusCode(200);
    }

    // default ReQuest Spec
    @Test
    public void complexMinLogsTest() {
        given().log().uri()
                .when().get(USERS + "2")
                .then().log().body().statusCode(200);
    }

    //  Separate ReQuest Spec
    @Test
    public void swapiSpecTest() {
        given().log().uri()
                .when().get(SWAPI_PEOPLE + "1")
                .then().spec(responseSpecificationGet).log().body();
    }

    @Test
    public void getQueryTest() {
        given().queryParam("page", 2).log().uri()
                .when().get(LIST_USERS)
                .then().log().body().statusCode(200);
    }

    @Test
    public void postCreateUserTest() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given().body(body).log().uri()
                .when().post(USERS)
                .then().spec(responseSpecificationPost).log().body();
    }

    @Test
    public void putUpdateUserTest() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given().body(body).log().uri()
                .when().put(USERS)
                .then().log().body().statusCode(200);
    }

    @Test
    public void deleteUserTest() {
        given().log().uri()
                .when().delete(USERS + "2")
                .then().spec(responseSpecificationDelete).log().body();
    }

    // TEST

    @Test
    public void swapiAssertNameTest() {
        given().log().uri()
                .when().get(SWAPI_PEOPLE)
                .then()
                .body("total", equalTo(12))
                .body("data.name[0]", equalTo("cerulean"))
                .body("data.name[0]", is("cerulean"))
                .body("support.text", equalTo("Tired of writing endless social media content? Let Content Caddy generate it for you."))
                .body("support.text", containsString("Let Content Caddy generate it for you."))
                .spec(responseSpecificationGet)
                .log().body();
    }

    @Test
    public void getBodyTest() {
        Response response = given().log().uri()
                .when().get(SWAPI_PEOPLE)
                .then()
                .extract().response();
        System.out.println("BODY ---->>> " + response.asString());
    }

    @Test
    public void getCookiesTest() {
        Response response = given().log().all()
                .when().get(SWAPI_PEOPLE)
                .then().log().all()
                .extract().response();
        Map<String, String> cookies = response.getCookies();

        System.out.println("Cookies ---->>> " + cookies);

        String someCookies = response.getCookie("report_to");
        System.out.println("Cookie ---->>> " + someCookies);
    }

    @Test
    public void getHeadersTest() {
        Response response = given().log().all()
                .when().get(SWAPI_PEOPLE)
                .then().log().all()
                .extract().response();
        Headers headers = response.getHeaders();

        System.out.println("Headers ---->>> " + headers);

        String someHeader = response.getHeader("server-timing");
        System.out.println("SomeHeader ---->>> " + someHeader);

        String contentType = response.contentType();
        System.out.println("contentType ---->>> " + contentType);
    }

    @Test
    public void schemaValidateTest() {
        given().log().all()
                .when().get(SWAPI_PEOPLE)
                .then().body(matchesJsonSchemaInClasspath("responseSchema.json")).log().body();

    }

    // GPATH

    @Test
    public void getMapElementTest() {
        Response response = given().spec(requestSpecificationSwapi).log().uri()
                .when().get(SWAPI_PEOPLE)
                .then().extract().response();
        System.out.println(response.asString());
        Map<String, ?> someObject = response
                .path("result.find {it.name = 'Luke Skywalker'}");
        System.out.println("---->>>>> " + someObject);
    }

    @Test
    public void getSingleElementWithSomeKeyTest() {
        Response response = given().spec(requestSpecificationSwapi).log().uri()
                .when().get(SWAPI_PEOPLE)
                .then().extract().response();
        System.out.println(response.asString());

        List films = response
                .path("results.findAll {it.films}.name");
        System.out.println("---->>>>> " + films);
    }

}
