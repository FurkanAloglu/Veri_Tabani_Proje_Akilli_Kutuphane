package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,unique = true)
    private String isbn;

    @Column()
    private Integer pageCount;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="categoryid",nullable = false)
    private Category category;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="authorid")
    private Author author;

}
