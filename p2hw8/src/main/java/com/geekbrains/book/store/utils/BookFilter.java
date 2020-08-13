package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BookFilter {
    private Specification<Book> spec;
    private String filterParams;

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey("maxPrice")) {
            spec = spec.and(BookSpecifications.priceLessOrEqualsThan(BigDecimal.valueOf(Double.parseDouble(params.get("maxPrice")))));
        }
        if (params.containsKey("minPrice")) {
            spec = spec.and(BookSpecifications.priceGreaterThanOrEqualsTo(BigDecimal.valueOf(Double.parseDouble(params.get("minPrice")))));
        }
        if (params.containsKey("titlePart")) {
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart")));
        }
        if (params.containsKey("genre")) {

            Specification<Book> genresSpecification = Specification.where(null);
            List<Genre> genresIds =
                    Arrays.stream(params.get("genre")
                            .split(","))
                            .map(String::trim).map(Long::parseLong)
                            .map(Genre::byId)
                            .collect(Collectors.toList());

            for (Genre genre : genresIds) {
                genresSpecification = genresSpecification.or(BookSpecifications.hasGenre(genre));
            }
            spec = spec.and(genresSpecification);
        }

    }

    public Specification<Book> getSpec() {
        return spec;
    }
}