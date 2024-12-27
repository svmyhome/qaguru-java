package helpers;

import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.MainPage;

import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        Response response = SupportRequest.getResponse(USER_NAME, PASSWORD);
        MainPage faviconPage = new MainPage();

        faviconPage.openFaviconPage(response);
//        getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
//        getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
//        getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));
    }

}
