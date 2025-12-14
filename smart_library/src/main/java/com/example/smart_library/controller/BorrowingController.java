package com.example.smart_library.controller;

import com.example.smart_library.dto.borrowing.BorrowingRequest;
import com.example.smart_library.dto.borrowing.BorrowingResponse;
import com.example.smart_library.model.User;
import com.example.smart_library.repository.UserRepository;
import com.example.smart_library.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<BorrowingResponse>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.findAll());
    }

    @PostMapping
    public ResponseEntity<BorrowingResponse> createBorrowing(@RequestBody BorrowingRequest borrowingRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Oturum açan kullanıcı bulunamadı"));

        borrowingRequest.setUserId(currentUser.getUserID());

        BorrowingResponse savedBorrowing = borrowingService.save(borrowingRequest);
        return new ResponseEntity<>(savedBorrowing, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(borrowingService.returnBook(id));
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