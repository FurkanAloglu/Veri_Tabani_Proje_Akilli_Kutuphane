package com.example.smart_library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smart_library.service.AuthorService;
import com.example.smart_library.dto.author.AuthorRequest;
import com.example.smart_library.dto.author.AuthorResponse;
import lombok.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class AuthorController{
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors(){
        List<AuthorResponse> authors= authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest){
        AuthorResponse savedAuthor = authorService.save(authorRequest);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable UUID id){
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id){
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
