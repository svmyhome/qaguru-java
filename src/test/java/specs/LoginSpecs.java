package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class LoginSpecs {

    public static RequestSpecification loginRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers();

    public static ResponseSpecification loginResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();

}
