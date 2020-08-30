package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Genre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Transactional
    public List<Genre> getAllGenres() {
        return Arrays.stream(Genre.values()).collect(Collectors.toList());
    }
}
