package models.books;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {
    private final String userId;
    private final List<Isbn> collectionOfIsbns;
}