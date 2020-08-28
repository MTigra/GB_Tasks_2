package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    private List<Genre> genres;

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishYear == book.publishYear &&
                id.equals(book.id) &&
                title.equals(book.title) &&
                description.equals(book.description) &&
                price.equals(book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, publishYear);
    }
}
