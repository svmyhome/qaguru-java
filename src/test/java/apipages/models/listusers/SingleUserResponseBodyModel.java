package apipages.models.listusers;

import lombok.Data;

@Data
public class SingleUserResponseBodyModel {
    private User data;
    private Support support;
}
