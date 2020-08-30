//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2020.08.24 at 03:00:29 AM MSK
//


package com.geekbrains.book.store;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;


/**
 * <p>Java class for genre.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="genre"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FANTASY"/&gt;
 *     &lt;enumeration value="DETECTIVE"/&gt;
 *     &lt;enumeration value="FICTION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "genre")
@XmlEnum
public enum Genre {
    FANTASY(1L, "Fantasy"), DETECTIVE(2L, "Detective"), FICTION(3L, "Fiction"), ADVENTURE(4L, "Adventure");;

    private final Long id;
    private final String name;

    Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static com.geekbrains.book.store.Genre byId(Long id) {
        return Arrays.stream(values()).filter(genre -> genre.id.equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static Genre byName(String name) {
        return Arrays.stream(values()).filter(genre -> genre.name.equals(name)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return name;
    }

    public static Genre fromValue(String v) {
        return byName(v);
    }

}
