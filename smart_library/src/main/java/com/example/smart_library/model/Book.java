package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,unique = true)
    private String isbn;

    @Column()
    private Integer pageCount;

    @ManyToOne(fetch= FetchType.EAGER)

    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Author author;

}
