package apipages.models.create_user;

import lombok.Data;

@Data
public class CreateUserRequestBodyModel {
    private final String name;
    private final String job;
}
