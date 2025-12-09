package com.example.smart_library.service;

import com.example.smart_library.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.example.smart_library.model.Category;
import com.example.smart_library.dto.category.CategoryRequest;
import com.example.smart_library.dto.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll(){
        return categoryRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CategoryResponse save(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    public Optional<CategoryResponse> findById(UUID id){
        return categoryRepository.findById(id).map(this::toResponse);
    }

    public void deleteById(UUID id){
        categoryRepository.deleteById(id);
    }

    private CategoryResponse toResponse(Category c){
        return CategoryResponse.builder().id(c.getCategoryID()).name(c.getName()).build();
    }
}
