package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Borrowing {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long borrowID;

    @Column(nullable=false)
    private LocalDateTime borrowDate;

    @Column(nullable=false)
    private LocalDateTime returnDate;

    @Column()
    private LocalDateTime relayReturnDate;

    @Column()
    private Boolean isPenalty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id",nullable=false)
    private Book book;

    @OneToOne(mappedBy="borrowing",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Penalty penalty;
}
