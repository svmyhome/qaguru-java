package helpers;

import static config.Constants.Credentials.PASSWORD;
import static config.Constants.Credentials.USER_NAME;
import static io.restassured.RestAssured.given;

public class BrowserstackHelper {


    //    curl -u "petrpetr_SsBEec:B3Ux6EuEBuEEWDGbLZeK" -X GET "https://api.browserstack.com/app-automate/sessions/fe145bd08b140a7c488eb274f66aa2577d1abaf5.json"


    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .auth().basic(USER_NAME, PASSWORD)
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");

    }

}
