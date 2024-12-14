package apipages.models.list_users;

import lombok.Data;

@Data
public class SingleUserResponseBodyModel {
    private User data;
    private Support support;
}
