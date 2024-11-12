package model.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyJackson1 {
    @JsonProperty("ID")
    private Integer id;
    @JsonProperty("Name")
    private String name;
    private List<String> skillList;
    private Book book;

    public Book getBook() {
        return book;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkillList() {
        return skillList;
    }

    @Override
    public String toString() {
        return "MyJackson1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skillList=" + skillList +
                ", book=" + book +
                '}';
    }
}
