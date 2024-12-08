package api.selenide;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiSelenideTest {

    @Test
    public void selenoidApiTest() {
        given().log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .body("total", is(20));
    }
}
