package emt223287.model.dto;

import emt223287.model.enums.Category;
import lombok.Data;

@Data
public class BookDto {
    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;
}