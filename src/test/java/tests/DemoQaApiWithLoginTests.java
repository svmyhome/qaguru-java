package tests;

import api.AccountApi;
import models.login.LoginRequestBodyModel;
import models.login.LoginResponseBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;

@Tag("API")
@DisplayName("API")
public class DemoQaApiWithLoginTests extends TestBase {

    AccountApi api = new AccountApi();
    LoginRequestBodyModel authBody = new LoginRequestBodyModel(USER_NAME, PASSWORD);

    @Test
    @DisplayName("Успешная авторизация в личном кабинет")
    public void successfullyLoginToBookStoreTest() {
        LoginResponseBodyModel response = api.getResponse(authBody);

        api.compareValues(authBody.getUserName(), response.getUsername())
                .compareValues("false", response.getIsActive());
    }

}
