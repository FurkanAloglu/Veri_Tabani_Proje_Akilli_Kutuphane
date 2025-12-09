package com.example.smart_library.dto.penalty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenaltyResponse {
    private UUID id;
    private BigDecimal penaltyFee;
    private Boolean isPaid;
    private LocalDateTime paymentDate;
    private UUID borrowingId;
}

