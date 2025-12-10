package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.repository.BorrowingRepository;
import com.example.smart_library.repository.UserRepository;
import com.example.smart_library.repository.BookRepository;
import com.example.smart_library.model.Borrowing;
import com.example.smart_library.model.User;
import com.example.smart_library.model.Book;
import com.example.smart_library.dto.borrowing.BorrowingRequest;
import com.example.smart_library.dto.borrowing.BorrowingResponse;
import lombok.RequiredArgsConstructor;
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

    public List<BorrowingResponse> findAll(){
        return borrowingRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public BorrowingResponse save(BorrowingRequest request){
        Borrowing borrowing = new Borrowing();
        borrowing.setBorrowDate(request.getBorrowDate());
        borrowing.setReturnDate(request.getReturnDate());
        borrowing.setRelayReturnDate(request.getRelayReturnDate());
        borrowing.setIsPenalty(request.getIsPenalty());

        User user = userRepository.findById(request.getUserId()).orElse(null);
        Book book = bookRepository.findById(request.getBookId()).orElse(null);
        borrowing.setUser(user);
        borrowing.setBook(book);

        Borrowing saved = borrowingRepository.save(borrowing);
        return convertToResponse(saved);
    }

    public Optional<BorrowingResponse> findById(UUID id){
        return borrowingRepository.findById(id).map(this::convertToResponse);
    }

    public void deleteById(UUID id){
        borrowingRepository.deleteById(id);
    }

    private BorrowingResponse convertToResponse(Borrowing b){
        return BorrowingResponse.builder()
                .id(b.getBorrowID())
                .borrowDate(b.getBorrowDate())
                .returnDate(b.getReturnDate())
                .relayReturnDate(b.getRelayReturnDate())
                .isPenalty(b.getIsPenalty())
                .userId(b.getUser() != null ? b.getUser().getUserID() : null)
                .bookId(b.getBook() != null ? b.getBook().getBookID() : null)
                .build();
    }
}
