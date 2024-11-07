package com.lenta;

import com.lenta.pages.LoginPage;
import com.lenta.pages.MainPage;
import org.junit.jupiter.api.Test;


public class LoginPageTests extends TestBase {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @Test
    void LoginTest() {
        mainPage.openMainPage();
        loginPage.clickLoginButton()
                .shouldAuthorizationWindowOpen()
                .shouldCodeButtonDisable()
                .setPhoneNumber()
                .shouldCodeButtonEnable();

    }
}
