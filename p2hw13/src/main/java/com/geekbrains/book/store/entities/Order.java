package com.geekbrains.book.store.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    public Order() {
    }

    public Order(User user, List<OrderItem> items) {
        this.user = user;
        orderItems = items;
        for (OrderItem o : orderItems){
            o.setOrder(this);
        }
        totalPrice = items.stream().map(OrderItem::getTotalPrice).reduce(new BigDecimal(0), BigDecimal::add);

    }


}
