package com.example.smart_library.service;

import com.example.smart_library.model.Category;
import com.example.smart_library.model.Author;
import org.springframework.stereotype.Service;
import com.example.smart_library.model.Book;
import com.example.smart_library.repository.BookRepository;
import com.example.smart_library.repository.CategoryRepository;
import com.example.smart_library.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import com.example.smart_library.dto.book.BookRequest;
import com.example.smart_library.dto.book.BookResponse;
import com.example.smart_library.dto.category.CategoryResponse;
import com.example.smart_library.dto.author.AuthorResponse;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public List<BookResponse> findAll(){
        return bookRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public BookResponse save(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPageCount(bookRequest.getPageCount());
        book.setStock(bookRequest.getStock());

        if (bookRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(bookRequest.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found: " + bookRequest.getCategoryId()));
            book.setCategory(category);
        }

        if (bookRequest.getAuthorId() != null) {
            Author author = authorRepository.findById(bookRequest.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found: " + bookRequest.getAuthorId()));
            book.setAuthor(author);
        }

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
        CategoryResponse categoryResp = null;
        if (book.getCategory() != null) {
            categoryResp = new CategoryResponse(book.getCategory().getCategoryID(), book.getCategory().getName());
        }

        AuthorResponse authorResp = null;
        if (book.getAuthor() != null) {
            authorResp = new AuthorResponse(book.getAuthor().getAuthorID(), book.getAuthor().getName(), book.getAuthor().getSurname());
        }

        return BookResponse.builder()
                .id(book.getBookID())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .pageCount(book.getPageCount())
                .stock(book.getStock())
                .category(categoryResp)
                .author(authorResp)
                .build();
    }
}
