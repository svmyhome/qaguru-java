package models.createuser;

import lombok.Data;

@Data
public class CreateUserResponseBodyModel {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
