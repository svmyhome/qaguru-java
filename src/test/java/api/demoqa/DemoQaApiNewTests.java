package api.demoqa;

import com.codeborne.selenide.Selenide;
import config.TestConfig;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;
import models.books.Isbn;
import models.login.LoginRequestBodyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static constants.Constants.Actions.*;
import static constants.Constants.HEADERS.APPLICATION_JSON;
import static constants.Constants.Path.ACCOUNT_V1;
import static constants.Constants.Path.BOOKSTORE_V1;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Tag("API")
public class DemoQaApiNewTests extends TestConfig {


    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    String firstName = "Vin";
    String lastName = "Dsel";
    String userName = "vindisel";
    String password = "!Qaz2wsx";

    @Test
    public void AuthorizeDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        step("Authorize user", () -> given().log().all()
                .contentType(APPLICATION_JSON)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().log().all()
                .statusCode(200)
                .body("username", is(userName)));
    }

    @Test
    public void AddItemToCartDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        Response authResponse = step("Authorize user", () -> given().log().all()
                .contentType(APPLICATION_JSON)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().log().all()
                .statusCode(200)
                .body("username", is(userName)).extract().response());

        Isbn isbn = new Isbn("9781449365035");
        List<Isbn> listIsbn = List.of(isbn);
        AddBookRequestModel bookData1 = new AddBookRequestModel(authResponse.path("userId"), listIsbn);

        // TODO VERIFY THAT CART EMPTY
        List<String> response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .when()
                .get(ACCOUNT_V1 + USER + authResponse.path("userId"))
                .then()
                .log().status()
                .log().body()
                .statusCode(200).extract().path("books");


        // TODO DELETE BOOK
        if (response.size() == 1) {
            DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn.getIsbn(), authResponse.path("userId"));
            given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + authResponse.path("token"))
                    .body(bookDataDelete)
                    .when()
                    .delete(BOOKSTORE_V1 + BOOK)
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
                    .delete(BOOKSTORE_V1 + BOOKS)
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
                .body(bookData1)
                .when()
                .post(BOOKSTORE_V1 + BOOKS)
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

    }

    // TODO ASSERT FROM UI

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

