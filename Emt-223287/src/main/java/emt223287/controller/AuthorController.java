package emt223287.controller;

import emt223287.model.Author;
import emt223287.model.dto.AuthorDto;
import emt223287.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> listAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);

        if (author == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(author);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto author) {
        Author newAuthor = authorService.addAuthor(author);

        if (newAuthor == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(newAuthor);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto author) {
        Author newAuthor = authorService.editAuthor(id, author);

        if (newAuthor == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(newAuthor);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);

        if (author == null) {
            return ResponseEntity.notFound().build();
        } else {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok(author);
        }
    }
}
