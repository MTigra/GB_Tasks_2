package com.martirosyan.p2hw3.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Lot> lot;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lot=" + lot +
                '}';
    }
}
