package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "publish_year")
    private int publishYear;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "genre_id")
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    public String getTitle(){
        return title;
    }
}
