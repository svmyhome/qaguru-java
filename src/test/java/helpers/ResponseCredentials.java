package helpers;

import io.restassured.response.Response;

public class ResponseCredentials {
    private static Response authResponse;

    public static void setAuthResponse(Response response) {
        authResponse = response;
    }

    public static Response getAuthResponse() {
        return authResponse;
    }
}
