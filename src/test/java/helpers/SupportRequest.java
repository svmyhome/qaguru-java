package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.login.LoginRequestBodyModel;

import static constants.Constants.Actions.LOGIN;
import static constants.Constants.Path.ACCOUNT_V1;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.LoginSpecs.account_v1_login_request_specification;
import static specs.LoginSpecs.account_v1_login_response_specification;

public class SupportRequest {

    @Step("Получить респонс для {userName} ")
    public static Response getRequest(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        Response authResponse = step("Authorize user", () -> given(account_v1_login_request_specification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(account_v1_login_response_specification)
                .body("username", is(userName)).extract().response());
        return authResponse;
    }

    @Step("Получить респонс для {userName} ")
    public static String getAuthorizationToken(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        String getToken = step("Authorize user", () -> given(account_v1_login_request_specification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(account_v1_login_response_specification)
                .body("username", is(userName)).extract().response().path("token"));
        return getToken;
    }

}
