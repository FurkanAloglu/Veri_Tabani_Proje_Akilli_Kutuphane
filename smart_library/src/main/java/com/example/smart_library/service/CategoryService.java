package com.example.smart_library.service;

import com.example.smart_library.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.example.smart_library.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(UUID id){
        return categoryRepository.findById(id);
    }

    public void deleteById(UUID id){
        categoryRepository.deleteById(id);
    }
}
