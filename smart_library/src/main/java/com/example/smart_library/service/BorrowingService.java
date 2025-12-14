package com.example.smart_library.service;

import com.example.smart_library.dto.borrowing.BorrowingRequest;
import com.example.smart_library.dto.borrowing.BorrowingResponse;
import com.example.smart_library.model.Book;
import com.example.smart_library.model.Borrowing;
import com.example.smart_library.model.User;
import com.example.smart_library.repository.BookRepository;
import com.example.smart_library.repository.BorrowingRepository;
import com.example.smart_library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<BorrowingResponse> findAll() {
        return borrowingRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public BorrowingResponse save(BorrowingRequest request) {
        Borrowing borrowing = new Borrowing();
        borrowing.setBorrowDate(LocalDateTime.now()); // Şu an alıyor
        borrowing.setReturnDate(null);
        borrowing.setRelayReturnDate(request.getRelayReturnDate() != null ? request.getRelayReturnDate() : LocalDateTime.now().plusDays(15));
        borrowing.setIsPenalty(false);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        borrowing.setUser(user);
        borrowing.setBook(book);

        Borrowing saved = borrowingRepository.save(borrowing);
        return convertToResponse(saved);
    }

    public BorrowingResponse returnBook(UUID borrowId) {
        Borrowing borrowing = borrowingRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Ödünç kaydı bulunamadı: " + borrowId));

        borrowing.setReturnDate(LocalDateTime.now());

        Borrowing saved = borrowingRepository.save(borrowing);
        return convertToResponse(saved);
    }

    public Optional<BorrowingResponse> findById(UUID id) {
        return borrowingRepository.findById(id).map(this::convertToResponse);
    }

    public void deleteById(UUID id) {
        borrowingRepository.deleteById(id);
    }

    private BorrowingResponse convertToResponse(Borrowing b) {
        return BorrowingResponse.builder()
                .borrowID(b.getBorrowID())
                .borrowDate(b.getBorrowDate())
                .returnDate(b.getReturnDate())
                .relayReturnDate(b.getRelayReturnDate())
                .isPenalty(b.getIsPenalty())
                .userId(b.getUser() != null ? b.getUser().getUserID() : null)
                .bookId(b.getBook() != null ? b.getBook().getBookID() : null)
                .build();
    }
}