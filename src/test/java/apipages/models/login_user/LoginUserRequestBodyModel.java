package apipages.models.login_user;

import lombok.Data;

@Data
public class LoginUserRequestBodyModel {
    private final String email;
    private final String password;
}
