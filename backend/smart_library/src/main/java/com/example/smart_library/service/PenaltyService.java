package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.repository.PenaltyRepository;
import com.example.smart_library.repository.BorrowingRepository;
import com.example.smart_library.model.Penalty;
import com.example.smart_library.model.Borrowing;
import com.example.smart_library.dto.penalty.PenaltyRequest;
import com.example.smart_library.dto.penalty.PenaltyResponse;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyService{
    private final PenaltyRepository penaltyRepository;
    private final BorrowingRepository borrowingRepository;

    public List<PenaltyResponse> findAll(){
        return penaltyRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PenaltyResponse save(PenaltyRequest request){
        Penalty penalty = new Penalty();
        penalty.setPenaltyFee(request.getPenaltyFee());
        penalty.setPaid(request.getIsPaid() != null ? request.getIsPaid() : false);
        penalty.setPaymentDate(request.getPaymentDate());
        Borrowing borrowing = borrowingRepository.findById(request.getBorrowingId()).orElse(null);
        penalty.setBorrowing(borrowing);
        Penalty saved = penaltyRepository.save(penalty);
        return toResponse(saved);
    }

    public Optional<PenaltyResponse> findById(UUID id){
        return penaltyRepository.findById(id).map(this::toResponse);
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
