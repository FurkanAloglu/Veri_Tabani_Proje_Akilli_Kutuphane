package com.example.smart_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_library.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
