package com.martirosyan.hw2.model;


import javax.persistence.*;

@Entity
@Table(name = "products_consumers")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @Column(name = "price")
    private Double price;

    public Purchase() {
    }

    public Purchase(Product product, Consumer consumer, Double price){
        this.consumer=consumer;
        this.price = price;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer customer) {
        this.consumer = customer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }



}
