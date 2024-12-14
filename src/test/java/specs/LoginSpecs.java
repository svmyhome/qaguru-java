package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class LoginSpecs {


    public static RequestSpecification listUsersRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().params()
            .log().headers()
            .log().body();

    public static ResponseSpecification listUsersResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification singleUserRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification singleUserResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();


    public static RequestSpecification notFoundUserRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification notFoundUserResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();


    public static RequestSpecification createUserRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification createUserResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();


    public static RequestSpecification loginSuccessfulRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification loginSuccessfulResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();


    public static RequestSpecification loginUnsuccessfulRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification loginUnsuccessfulResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectHeader("Content-type", "application/json; charset=utf-8")
            .log(STATUS)
            .log(BODY)
            .build();

}
