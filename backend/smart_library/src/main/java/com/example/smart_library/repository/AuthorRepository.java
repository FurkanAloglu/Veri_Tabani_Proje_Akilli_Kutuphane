package com.example.smart_library.repository;

import com.example.smart_library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author,UUID>{







}
