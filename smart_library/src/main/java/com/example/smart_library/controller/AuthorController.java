package com.example.smart_library.controller;

import com.example.smart_library.model.Author;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smart_library.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class AuthorController{
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authors= authorService.findAll();

        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author savedAuthor = authorService.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
