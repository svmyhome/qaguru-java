package models.books;

import lombok.Data;

@Data
public class DeleteBookRequestModel {
    private final String isbn;
    private final String userId;
}