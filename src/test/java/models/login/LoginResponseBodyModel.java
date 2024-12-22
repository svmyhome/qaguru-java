package models.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseBodyModel {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    @JsonProperty("created_date")
    private String createdDate;
    private String isActive;
}

//
//{
//        "userId": "5b831716-0b1e-4c33-8ab7-c85429a698e7",
//        "username": "vindisel",
//        "password": "!Qaz2wsx",
//        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InZpbmRpc2VsIiwicGFzc3dvcmQiOiIhUWF6MndzeCIsImlhdCI6MTczNDgwMzMwM30.h_g6tM5UkSnjYYE8EGqQBbSQht2aLMxuMTFlUhVrvMk",
//        "expires": "2024-12-28T17:48:23.000Z",
//        "created_date": "2024-12-21T17:32:39.000Z",
//        "isActive": false
//        }