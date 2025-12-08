package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.model.Book;
import com.example.smart_library.repository.BookRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}
