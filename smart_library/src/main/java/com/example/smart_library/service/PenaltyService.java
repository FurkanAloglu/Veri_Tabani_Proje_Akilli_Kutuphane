package com.example.smart_library.service;

import com.example.smart_library.dto.penalty.PenaltyResponse;
import com.example.smart_library.model.Penalty;
import com.example.smart_library.repository.PenaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyService {
    private final PenaltyRepository penaltyRepository;

    public List<PenaltyResponse> findAll() {
        return penaltyRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PenaltyResponse payPenalty(UUID penaltyId) {
        Penalty penalty = penaltyRepository.findById(penaltyId)
                .orElseThrow(() -> new RuntimeException("Ceza bulunamadı: " + penaltyId));

        penalty.setPaid(true);
        penalty.setPaymentDate(LocalDateTime.now());

        Penalty saved = penaltyRepository.save(penalty);
        return toResponse(saved);
    }

    public Optional<PenaltyResponse> findById(UUID id) {
        return penaltyRepository.findById(id).map(this::toResponse);
    }

    public void deleteById(UUID id) {
        penaltyRepository.deleteById(id);
    }

    private PenaltyResponse toResponse(Penalty p) {
        return PenaltyResponse.builder()
                .id(p.getPenaltyId())
                .penaltyFee(p.getPenaltyFee())
                .isPaid(p.isPaid())
                .paymentDate(p.getPaymentDate())
                .borrowingId(p.getBorrowing() != null ? p.getBorrowing().getBorrowID() : null)
                .build();
    }
}