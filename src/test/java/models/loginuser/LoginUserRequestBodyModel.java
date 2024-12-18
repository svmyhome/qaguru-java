package models.loginuser;

import lombok.Data;

@Data
public class LoginUserRequestBodyModel {
    private final String email;
    private final String password;
}
