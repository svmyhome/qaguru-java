package tests;

import models.login.LoginRequestBodyModel;
import models.login.LoginResponseBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.AccountApi.compareValues;
import static constants.Constants.ApiActions.ACCOUNT_V1_LOGIN;
import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpecs.baseRequestSpecification;
import static specs.LoginSpecs.statusCode200ResponseSpecification;

@Tag("API")
@DisplayName("API")
public class DemoQaApiWithLoginTests extends TestBase {


    @Test
    @DisplayName("Успешная авторизация в личном кабинет")
    public void successfullyLoginToBookStoreTest() {

        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        LoginResponseBodyModel response = step("Запрос на авторизацию пользователя", () -> given(baseRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1_LOGIN)
                .then().spec(statusCode200ResponseSpecification)
                .extract().as(LoginResponseBodyModel.class));

        step("Успешное получение ответа", () -> {
            compareValues(authBody.getUserName(), response.getUsername());
            compareValues("false", response.getIsActive());
        });
    }

}