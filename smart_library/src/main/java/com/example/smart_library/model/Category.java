package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID categoryID;

    @Column(nullable =false)
    private String name;
}
