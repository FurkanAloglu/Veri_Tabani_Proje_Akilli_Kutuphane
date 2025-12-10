package com.example.smart_library.dto.borrowing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRequest {
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private LocalDateTime relayReturnDate;
    private Boolean isPenalty;
}

