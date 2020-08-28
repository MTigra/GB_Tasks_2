package com.martirosyan.hw2.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "consumers")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_consumers",
            joinColumns = @JoinColumn(name = "consumer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Purchase> purchases;

    public Consumer() {
    }

    public Consumer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(id, consumer.id) &&
                Objects.equals(name, consumer.name) &&
                Objects.equals(products, consumer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}


