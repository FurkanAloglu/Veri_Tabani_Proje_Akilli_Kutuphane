package com.example.smart_library.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.smart_library.dto.author.AuthorResponse;
import com.example.smart_library.dto.category.CategoryResponse;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private UUID id;
    private String title;
    private String isbn;
    private Integer pageCount;
    private CategoryResponse category;
    private AuthorResponse author;
}
