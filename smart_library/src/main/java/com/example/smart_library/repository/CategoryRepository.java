package com.example.smart_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_library.model.Category;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category,UUID> {
}
