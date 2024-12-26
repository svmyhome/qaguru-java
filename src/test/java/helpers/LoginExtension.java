package helpers;

import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        Response response = SupportRequest.getResponse(USER_NAME, PASSWORD);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));
    }

}
