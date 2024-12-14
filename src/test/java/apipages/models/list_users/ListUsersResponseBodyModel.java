package apipages.models.list_users;

import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseBodyModel {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
    private Support support;
}
