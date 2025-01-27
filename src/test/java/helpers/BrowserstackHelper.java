package helpers;

import java.net.MalformedURLException;
import java.net.URL;

import static helpers.CustomAllureListener.withCustomTemplates;
import static helpers.ProjectSettings.Credentials.PASSWORD;
import static helpers.ProjectSettings.Credentials.USER_NAME;
import static io.restassured.RestAssured.given;

public class BrowserstackHelper {

    public static URL getBrowserstackUrl() {
        try {
            return new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", USER_NAME, PASSWORD));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given().filter(withCustomTemplates())
                .auth().basic(USER_NAME, PASSWORD)
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");

    }

}
