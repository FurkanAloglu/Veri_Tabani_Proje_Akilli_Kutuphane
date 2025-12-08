package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.model.Author;
import com.example.smart_library.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository =authorRepository;
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author save(Author author){
        return authorRepository.save(author);//Create ve Update
    }

    public Optional<Author> findById(Long id){
        return authorRepository.findById(id); //Read
    }

    public void deleteById(Long id){
        authorRepository.deleteById(id);//Delete
    }

}
