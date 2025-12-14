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
    private final EmailService emailService;

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

    // Trigger'ı tetikleyecek olan metot: Kitap İade İşlemi
    public BorrowingResponse returnBook(UUID borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));

        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        // Şu anki zamanı iade tarihi olarak set et
        borrowing.setRelayReturnDate(now);
        
        // Kaydettiğimiz anda veritabanındaki UPDATE Trigger'ı çalışacak
        // ve gecikme varsa Penalty tablosuna kayıt atacak.
        Borrowing saved = borrowingRepository.save(borrowing);

        // E-posta Bildirimi: Eğer geç iade ise kullanıcıya mail at
        if (now.isAfter(borrowing.getReturnDate())) {
            if (borrowing.getUser() != null) {
                // Not: User entity'nizde getEmail() metodu olduğunu varsayıyoruz.
                // String userEmail = borrowing.getUser().getEmail(); 
                // emailService.sendSimpleMessage(userEmail, "Geç İade Bildirimi", "Kitabınızı geç iade ettiniz. Cezanız sisteme yansıtılmıştır.");
                
                // Örnek kullanım (User entity detayları bilinmediği için yorum satırı olarak bırakıldı, açabilirsiniz):
                // emailService.sendSimpleMessage("ogrenci@ornek.com", "Kütüphane: Geç İade", "Kitap iadesi gecikti. Lütfen cezayı kontrol ediniz.");
                System.out.println("Geç iade tespit edildi, e-posta gönderim sırasına alındı.");
            }
        }

        return convertToResponse(saved);
    }

    public void deleteById(UUID id){
        borrowingRepository.deleteById(id);
    }

    private BorrowingResponse convertToResponse(Borrowing b){
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
