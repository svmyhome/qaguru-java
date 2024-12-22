package api.demoqa;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;

public class DemoQaApiTests {

    //    First Name :    Vin
//    Last Name :     Dsel
    String userName = "vindisel";
    //    Password : !Qaz2wsx
    String payload = "{\"userName\":\"vindisel\",\"password\":\"!Qaz2wsx\"}";

    @Test
    public void AuthorizeDemoQa() {
        step("Authorize user", () -> given().log().all()
                .contentType("application/json")
                .body(payload)
                .when().post("https://demoqa.com/Account/v1/Login")
                .then().log().all()
                .statusCode(200)
                .body("username", is(userName)));
    }

    @Test
    public void AddItemToCartDemoQa() {
        Response authResponse = step("Authorize user", () -> given().log().all()
                .contentType("application/json")
                .body(payload)
                .when().post("https://demoqa.com/Account/v1/Login")
                .then().log().all()
                .statusCode(200)
                .body("username", is(userName)).extract().response());

        String isbn = "9781449365035";
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.path("userId"), isbn);


        // TODO VERIFY THAT CART EMPTY
        List<String> response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .when()
                .get("https://demoqa.com/Account/v1/User/" + authResponse.path("userId"))
                .then()
                .log().status()
                .log().body()
                .statusCode(200).extract().path("books");

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + response);

        // TODO DELETE BOOK
        if (response.size() == 1) {
            String bookDataDelete = format("{\n" +
                    "  \"isbn\": \"%s\",\n" +
                    "  \"userId\": \"%s\"\n" +
                    "}", isbn, authResponse.path("userId"));
            given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + authResponse.path("token"))
                    .body(bookDataDelete)
                    .when()
                    .delete("https://demoqa.com/BookStore/v1/Book")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(204);
        } else if (response.size() > 1) {
            given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + authResponse.path("token"))
                    .queryParam("UserId", "5b831716-0b1e-4c33-8ab7-c85429a698e7")
                    .when()
                    .delete("https://demoqa.com/BookStore/v1/Books")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(204);
        }


        // TODO ADD BOOK
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .post("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

    }

}
//    {
//        "userId": "5b831716-0b1e-4c33-8ab7-c85429a698e7",
//            "username": "vindisel",
//            "password": "!Qaz2wsx",
//            "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InZpbmRpc2VsIiwicGFzc3dvcmQiOiIhUWF6MndzeCIsImlhdCI6MTczNDgwMzMwM30.h_g6tM5UkSnjYYE8EGqQBbSQht2aLMxuMTFlUhVrvMk",
//            "expires": "2024-12-28T17:48:23.000Z",
//            "created_date": "2024-12-21T17:32:39.000Z",
//            "isActive": false
//    }

