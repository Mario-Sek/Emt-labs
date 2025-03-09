package emt223287.service;


import emt223287.model.Book;
import emt223287.model.dto.BookDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book addBook(BookDto book);

    Book editBook(Long id, BookDto book);

    void deleteBook(Long id);

    void markBookAsTaken(Long id);

}
