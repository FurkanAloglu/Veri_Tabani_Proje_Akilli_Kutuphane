package com.example.smart_library.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long authorID;

    @Column
    private String name;

    @Column
    private String surname;

    @OneToMany(mappedBy="author",cascade = CascadeType.ALL)
    private List<Book> books;
}
