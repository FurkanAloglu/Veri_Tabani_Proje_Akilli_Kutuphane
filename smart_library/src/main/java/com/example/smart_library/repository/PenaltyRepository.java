package com.example.smart_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart_library.model.Penalty;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty,Long> {


}
