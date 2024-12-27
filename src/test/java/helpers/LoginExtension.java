package helpers;

import api.AccountApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.MainPage;

import static constants.Constants.Credentials.PASSWORD;
import static constants.Constants.Credentials.USER_NAME;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        Response response = AccountApi.getResponse(USER_NAME, PASSWORD);
        MainPage faviconPage = new MainPage();

        ResponseCredentials.setAuthResponse(response);

        faviconPage.openFaviconPage(response);
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("authResponse", response);
    }

}
