package com.example.smart_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_library.model.User;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {


}
