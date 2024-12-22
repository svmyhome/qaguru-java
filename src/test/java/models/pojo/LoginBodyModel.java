package models.pojo;

public class LoginBodyModel {
    public LoginBodyModel(String name, String job) {
        this.name = name;
        this.job = job;
    }

    String name;
    String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
