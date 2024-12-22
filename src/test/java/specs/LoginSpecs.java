package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static constants.Constants.HEADERS.APPLICATION_JSON_UTF_8;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class LoginSpecs {

    public static RequestSpecification account_v1_login_request_specification = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static ResponseSpecification account_v1_login_response_specification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectHeader("Content-type", APPLICATION_JSON_UTF_8)
            .log(STATUS)
            .log(BODY)
            .build();

}
