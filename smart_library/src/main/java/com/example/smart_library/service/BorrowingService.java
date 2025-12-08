package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.repository.BorrowingRepository;
import com.example.smart_library.model.Borrowing;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BorrowingRepository borrowingRepository){
        this.borrowingRepository = borrowingRepository;
    }

    public List<Borrowing> findAll(){
        return borrowingRepository.findAll();
    }

    public Borrowing save(Borrowing borrowing){
        return borrowingRepository.save(borrowing);
    }

    public Optional<Borrowing> findById(UUID id){
        return borrowingRepository.findById(id);
    }

    public void deleteById(UUID id){
        borrowingRepository.deleteById(id);
    }
}
