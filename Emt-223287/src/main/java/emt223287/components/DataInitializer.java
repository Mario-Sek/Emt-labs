package emt223287.components;

import emt223287.model.Author;
import emt223287.model.Book;
import emt223287.model.Country;
import emt223287.model.enums.Category;
import emt223287.repository.AuthorRepository;
import emt223287.repository.BookRepository;
import emt223287.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {

        countryRepository.save(new Country("USA", "North America"));
        countryRepository.save(new Country("Germany", "Europe"));
        countryRepository.save(new Country("Japan", "Asia"));
        countryRepository.flush();
        List<Country> countries = countryRepository.findAll();

        authorRepository.save(new Author("John", "Doe", countries.get(0)));
        authorRepository.save(new Author("Jane", "Smith", countries.get(1)));
        authorRepository.save(new Author("Alice", "Johnson", countries.get(2)));
        authorRepository.flush();
        List<Author> authors = authorRepository.findAll();

        bookRepository.save(new Book("Book A", Category.BIOGRAPHY, authors.get(0), 10));
        bookRepository.save(new Book("Book B", Category.HISTORY, authors.get(1), 5));
        bookRepository.save(new Book("Book C", Category.FANTASY, authors.get(2), 8));
    }
}
