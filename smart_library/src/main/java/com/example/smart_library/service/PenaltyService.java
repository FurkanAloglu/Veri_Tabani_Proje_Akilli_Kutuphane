package com.example.smart_library.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import com.example.smart_library.repository.PenaltyRepository;
import com.example.smart_library.repository.BorrowingRepository;
import com.example.smart_library.model.Penalty;
import com.example.smart_library.model.Borrowing;
import com.example.smart_library.dto.penalty.PenaltyRequest;
import com.example.smart_library.dto.penalty.PenaltyResponse;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyService{
    private final PenaltyRepository penaltyRepository;
    private final BorrowingRepository borrowingRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<PenaltyResponse> findAll(){
        return penaltyRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PenaltyResponse save(PenaltyRequest request){
        Penalty penalty = new Penalty();
        penalty.setPenaltyFee(request.getPenaltyFee());
        penalty.setPaid(request.getIsPaid() != null ? request.getIsPaid() : false);
        penalty.setPaymentDate(request.getPaymentDate());
        
        Borrowing borrowing = borrowingRepository.findById(request.getBorrowingId())
                .orElseThrow(() -> new RuntimeException("Borrowing record not found with id: " + request.getBorrowingId()));
        
        penalty.setBorrowing(borrowing);
        Penalty saved = penaltyRepository.save(penalty);
        return toResponse(saved);
    }

    public Optional<PenaltyResponse> findById(UUID id){
        return penaltyRepository.findById(id).map(this::toResponse);
    }

    // Stored Procedure Çağrısı: Toplam Gelir
    public BigDecimal calculateTotalRevenue() {
        // PostgreSQL Stored Procedure çağrısı
        // Not: Procedure 'OUT' parametresi döndürüyorsa bu şekilde çağrılabilir
        // veya Repository katmanında @Procedure anotasyonu kullanılabilir.
        // Burada native query örneği gösteriyorum:
        Object result = entityManager.createNativeQuery("CALL sp_calculate_total_revenue(?)").getSingleResult();
        return result != null ? (BigDecimal) result : BigDecimal.ZERO;
    }

    public void deleteById(UUID id){
        penaltyRepository.deleteById(id);
    }

    private PenaltyResponse toResponse(Penalty p){
        return PenaltyResponse.builder()
                .id(p.getPenaltyId())
                .penaltyFee(p.getPenaltyFee())
                .isPaid(p.isPaid())
                .paymentDate(p.getPaymentDate())
                .borrowingId(p.getBorrowing() != null ? p.getBorrowing().getBorrowID() : null)
                .build();
    }

}
