package helpers;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static constants.Constants.CREDENTIALS.PASSWORD;
import static constants.Constants.CREDENTIALS.USER_NAME;

public class LoginExtension implements BeforeEachCallback {

    private static final String ALLURE_TESTOPS_SESSION = "ALLURE_TESTOPS_SESSION";

    @Override
    public void beforeEach(ExtensionContext context) {
//        String cookies = SupportRequest.getRequest(USER_NAME, PASSWORD);
        String cookies = SupportRequest.getRequest(USER_NAME, PASSWORD).asString();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, cookies));
    }

}
