package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.login.LoginRequestBodyModel;

import java.util.List;

import static constants.Constants.ApiActions.ACCOUNT_V1_LOGIN;
import static constants.Constants.ApiActions.ACCOUNT_V1_USER;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.baseRequestSpecification;
import static specs.LoginSpecs.statusCode200ResponseSpecification;

public class AccountApi {

    @Step("Получить респонс для  пользователя {userName} ")
    public static Response getResponse(String userName, String password) {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(userName, password);
        return step("Запрос и ответ", () -> given(baseRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1_LOGIN)
                .then().spec(statusCode200ResponseSpecification)
                .body("username", is(userName)).extract().response());
    }

    @Step("Запрос информации профиля")
    public static List<String> getProfileInfo(String bearerToken, String userId) {
        return given(baseRequestSpecification)
                .header("Authorization", bearerToken)
                .when()
                .get(ACCOUNT_V1_USER + userId)
                .then()
                .spec(statusCode200ResponseSpecification)
                .statusCode(200).extract().path("books");
    }


    @Step("Значение совпадает c {expected}")
    public static void compareValues(String expected, String actual) {
        assertEquals(expected, actual);
    }


}
