package com.geekbrains.book.store.repositories.converters;

import com.geekbrains.book.store.entities.Genre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, Long> {
    @Override
    public Long convertToDatabaseColumn(Genre attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getId();
    }

    @Override
    public Genre convertToEntityAttribute(Long dbData) {
        return Genre.byId(dbData);
    }
}
