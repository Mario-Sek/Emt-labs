package emt223287.service;


import emt223287.model.Author;
import emt223287.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author addAuthor(AuthorDto author);

    Author editAuthor(Long id, AuthorDto author);

    void deleteAuthor(Long id);
}
