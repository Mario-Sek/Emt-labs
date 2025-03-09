package emt223287.service.impl;



import emt223287.model.Author;
import emt223287.model.Country;
import emt223287.model.dto.AuthorDto;
import emt223287.repository.AuthorRepository;
import emt223287.service.AuthorService;
import emt223287.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author addAuthor(AuthorDto author) {
        Author a = new Author();
        return saveAuthor(author, a);
    }

    @Override
    public Author editAuthor(Long id, AuthorDto author) {
        Author a = authorRepository.findById(id).orElse(null);

        if (a == null) {
            return null;
        }

        return saveAuthor(author, a);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private Author saveAuthor(AuthorDto author, Author a) {
        Country c = countryService.getCountryById(author.getCountry());

        if (c == null) {
            return null;
        }

        a.setName(author.getName());
        a.setSurname(author.getSurname());
        a.setCountry(c);

        return authorRepository.save(a);
    }


}

