package com.example.smart_library.service;

import com.example.smart_library.dto.author.AuthorRequest;
import com.example.smart_library.dto.author.AuthorResponse;
import com.example.smart_library.model.Author;
import com.example.smart_library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import lombok.*;
import com.example.smart_library.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public AuthorResponse save(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setSurname(request.getSurname());

        Author savedAuthor = authorRepository.save(author);

        return convertToResponse(savedAuthor);
    }

    public Optional<AuthorResponse> findById(UUID id){
        return authorRepository.findById(id).map(this::convertToResponse);
    }

    public void deleteById(UUID id){
        authorRepository.deleteById(id);
    }

    private AuthorResponse convertToResponse(Author author) {
        return new AuthorResponse(
                author.getAuthorID(),
                author.getName(),
                author.getSurname());
    }
}