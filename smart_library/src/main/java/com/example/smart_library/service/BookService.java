package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.model.Book;
import com.example.smart_library.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import com.example.smart_library.dto.book.BookRequest;
import com.example.smart_library.dto.book.BookResponse;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookResponse> findAll(){
        return bookRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public BookResponse save(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPageCount(bookRequest.getPageCount());
        // set category/author by ids will require fetching entities; for now leave null or implement later
        Book saved = bookRepository.save(book);
        return convertToResponse(saved);
    }

    public Optional<BookResponse> findById(UUID id){
        return bookRepository.findById(id).map(this::convertToResponse);
    }

    public void deleteById(UUID id){
        bookRepository.deleteById(id);
    }

    private BookResponse convertToResponse(Book book){
        return BookResponse.builder()
                .id(book.getBookID())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .pageCount(book.getPageCount())
                .id(book.getAuthor() != null ? book.getAuthor().getAuthorID() : null)
                .id(book.getCategory() != null ? book.getCategory().getCategoryID() : null)
                .build();
    }
}
