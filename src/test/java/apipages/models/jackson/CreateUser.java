package apipages.models.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUser {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public CreateUser(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
