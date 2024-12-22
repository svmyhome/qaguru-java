package api.demoqa;

import com.codeborne.selenide.Selenide;
import config.TestConfig;
import io.restassured.response.Response;
import models.books.AddBookRequestModel;
import models.books.DeleteBookRequestModel;
import models.books.Isbn;
import models.login.LoginRequestBodyModel;
import models.login.LoginResponseBodyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static constants.Constants.Actions.*;
import static constants.Constants.CREDENTIALS.PASSWORD;
import static constants.Constants.CREDENTIALS.USER_NAME;
import static constants.Constants.Path.ACCOUNT_V1;
import static constants.Constants.Path.BOOKSTORE_V1;
import static helpers.SupportRequest.getAuthorizationToken;
import static helpers.SupportRequest.getRequest;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.account_v1_login_request_specification;
import static specs.LoginSpecs.account_v1_login_response_specification;

@Tag("API")
public class DemoQaApiNewTests extends TestConfig {


    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    String firstName = "Vin";
    String lastName = "Dsel";

    @Test
    public void AuthorizeDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        LoginResponseBodyModel response = step("Authorize user", () -> given(account_v1_login_request_specification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(account_v1_login_response_specification)
                .extract().as(LoginResponseBodyModel.class));

        step("Assert that ", () -> {
            assertEquals(authBody.getUserName(), response.getUsername());
            assertEquals("false", response.getIsActive());
        });
    }

    @Test
    public void AddItemToCartDemoQa() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        Response authResponse = getRequest(USER_NAME, PASSWORD);
        String authResponse1 = getAuthorizationToken(USER_NAME, PASSWORD);

        String userId = authResponse.path("userId");
        String token = getAuthorizationToken(USER_NAME, PASSWORD);

        Isbn isbn = new Isbn("9781449365035");
        List<Isbn> listIsbn = List.of(isbn);
        AddBookRequestModel bookData1 = new AddBookRequestModel(userId, listIsbn);

        // TODO VERIFY THAT CART EMPTY
        List<String> response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ACCOUNT_V1 + USER + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200).extract().path("books");


        // TODO DELETE BOOK
        if (response.size() == 1) {
            DeleteBookRequestModel bookDataDelete = new DeleteBookRequestModel(isbn.getIsbn(), userId);
            given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + token)
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
                    .header("Authorization", "Bearer " + token)
                    .queryParam("UserId", userId)
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
                .header("Authorization", "Bearer " + token)
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

