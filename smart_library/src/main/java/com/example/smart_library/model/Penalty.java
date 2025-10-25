package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Penalty{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long penaltyId;

    @Column(nullable = false,scale=2,precision=6)
    private BigDecimal penaltyFee;

    @Column(nullable = false)
    private boolean isPaid=false;

    @Column
    private LocalDateTime paymentDate;

    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    @JoinColumn(name="borrowing_id",unique = true,nullable = false)
    private Borrowing borrowing;

}
