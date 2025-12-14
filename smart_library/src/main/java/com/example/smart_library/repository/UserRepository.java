package com.example.smart_library.repository;

import com.example.smart_library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT get_user_total_debt(:userId)", nativeQuery = true)
    BigDecimal getUserTotalDebt(@Param("userId") UUID userId);
}