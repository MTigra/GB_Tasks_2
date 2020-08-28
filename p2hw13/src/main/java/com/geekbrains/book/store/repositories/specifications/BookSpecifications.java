package com.geekbrains.book.store.repositories.specifications;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Book_;
import com.geekbrains.book.store.entities.Genre;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public class BookSpecifications {
    public static Specification<Book> priceLessOrEqualsThan(BigDecimal maxPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(Book_.price), maxPrice); // where b.price <= maxPrice
    }

    public static Specification<Book> titleLike(String titlePart) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get(Book_.title), String.format("%%%s%%", titlePart)); // where b.title like %titlePart%
    }

    public static Specification<Book> priceGreaterThanOrEqualsTo(BigDecimal minPrice) {
        return (Specification<Book>) (root, crieriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(Book_.price), minPrice);
    }

    public static Specification<Book> hasGenre(Genre genres) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isMember(genres, root.get(Book_.genres));
    }
}