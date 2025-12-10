package com.example.smart_library.controller;

import com.example.smart_library.dto.borrowing.BorrowingRequest;
import com.example.smart_library.dto.borrowing.BorrowingResponse;
import com.example.smart_library.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @GetMapping
    public ResponseEntity<List<BorrowingResponse>> getAllBorrowings() {
        List<BorrowingResponse> borrowings = borrowingService.findAll();
        return ResponseEntity.ok(borrowings);
    }

    @PostMapping
    public ResponseEntity<BorrowingResponse> createBorrowing(@RequestBody BorrowingRequest borrowingRequest) {
        BorrowingResponse savedBorrowing = borrowingService.save(borrowingRequest);
        return new ResponseEntity<>(savedBorrowing, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingResponse> getBorrowingById(@PathVariable UUID id) {
        return borrowingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable UUID id) {
        borrowingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
