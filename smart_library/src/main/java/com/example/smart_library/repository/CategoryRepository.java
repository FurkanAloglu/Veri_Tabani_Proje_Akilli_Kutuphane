package com.example.smart_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_library.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
