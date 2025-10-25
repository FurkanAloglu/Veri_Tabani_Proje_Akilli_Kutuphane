package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long categoryID;

    @Column(nullable =false)
    private String name;
}
