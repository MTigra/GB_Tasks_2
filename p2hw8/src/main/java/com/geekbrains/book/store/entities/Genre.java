package com.geekbrains.book.store.entities;

import java.util.Arrays;

public enum Genre {
        FANTASY(1L, "Fantasy"), DETECTIVE(2L, "Detective"), FICTION(3L, "Fiction");

    private final Long id;
    private final String name;

    Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genre byId(Long id) {
        return Arrays.stream(Genre.values()).filter(genre -> genre.id.equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static Genre byName(String name){
        return Arrays.stream(Genre.values()).filter(genre -> genre.name.equals(name)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
