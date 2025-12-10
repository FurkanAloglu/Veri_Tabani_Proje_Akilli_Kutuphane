package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Borrowing {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID borrowID;

    @Column(nullable=false)
    private LocalDateTime borrowDate;

    @Column(nullable=false)
    private LocalDateTime returnDate;

    @Column()
    private LocalDateTime relayReturnDate;

    @Column()
    private Boolean isPenalty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userID",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookID",nullable=false)
    private Book book;

    @OneToOne(mappedBy="borrowing",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Penalty penalty;
}
