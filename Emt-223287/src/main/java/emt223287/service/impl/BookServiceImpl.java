package emt223287.service.impl;

import emt223287.model.Author;
import emt223287.model.Book;
import emt223287.model.dto.BookDto;
import emt223287.repository.BookRepository;
import emt223287.service.AuthorService;
import emt223287.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(BookDto book) {
        Book b = new Book();
        return saveBook(book, b);
    }

    @Override
    public Book editBook(Long id, BookDto book) {
        Book b = bookRepository.findById(id).orElse(null);

        if (b == null) {
            return null;
        }

        return saveBook(book, b);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book b = bookRepository.findById(id).orElse(null);

        if (b == null) {
            return;
        }

        b.setAvailableCopies(b.getAvailableCopies() == 0 ? 0 : b.getAvailableCopies() - 1);

        bookRepository.save(b);
    }

    private Book saveBook(BookDto book, Book b) {
        Author a = authorService.getAuthorById(book.getAuthor());

        if (a == null) {
            return null;
        }

        b.setName(book.getName().isEmpty() ? "Unnamed Book" : book.getName());
        b.setCategory(book.getCategory());
        b.setAuthor(a);
        b.setAvailableCopies(book.getAvailableCopies() >= 0 ? book.getAvailableCopies() : 0);

        return bookRepository.save(b);
    }
}
