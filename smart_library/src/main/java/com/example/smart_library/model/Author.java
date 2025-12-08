package com.example.smart_library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;


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
    @JsonIgnore
    private List<Book> books;
}
