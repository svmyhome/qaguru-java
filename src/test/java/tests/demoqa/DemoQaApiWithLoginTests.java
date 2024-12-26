package tests.demoqa;

import com.codeborne.selenide.Selenide;
import models.login.LoginRequestBodyModel;
import models.login.LoginResponseBodyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static constants.Constants.ApiActions.LOGIN;
import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;
import static constants.Constants.Path.ACCOUNT_V1;
import static helpers.SupportRequest.compareValues;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpecs.accountV1LoginRequestSpecification;
import static specs.LoginSpecs.accountV1LoginResponseSpecification;

@Tag("API")
@DisplayName("API")
public class DemoQaApiWithLoginTests extends TestBase {


    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Успешная авторизация в личном кабинет")
    public void successfullyLoginToBookStoreTest() {
        LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);
        LoginResponseBodyModel response = step("Запрос на авторизацию пользователя", () -> given(accountV1LoginRequestSpecification)
                .body(authBody)
                .when().post(ACCOUNT_V1 + LOGIN)
                .then().spec(accountV1LoginResponseSpecification)
                .extract().as(LoginResponseBodyModel.class));

        step("Успешное получение ответа", () -> {
            compareValues(authBody.getUserName(), response.getUsername());
            compareValues("false", response.getIsActive());
        });
    }

}
