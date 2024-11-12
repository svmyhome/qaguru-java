package model.jackson;

public class Book {
    private String title;
    private String name;
    private Integer number;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}

