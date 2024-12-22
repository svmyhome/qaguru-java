package models.createuser;

import lombok.Data;

@Data
public class CreateUserRequestBodyModel {
    private final String name;
    private final String job;
}
