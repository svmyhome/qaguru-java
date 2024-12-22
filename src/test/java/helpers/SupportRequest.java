package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.login.LoginRequestBodyModel;

import static constants.Constants.Actions.LOGIN;
import static constants.Constants.HEADERS.APPLICATION_JSON;
import static constants.Constants.Path.ACCOUNT_V1;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SupportRequest {

    @Step("Получить респонс для {userName} ")
    public static Response getRequest(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        Response authResponse = step("Authorize user", () -> given().log().all()
                .contentType(APPLICATION_JSON)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().log().all()
                .statusCode(200)
                .body("username", is(userName)).extract().response());
        return authResponse;
    }

}
