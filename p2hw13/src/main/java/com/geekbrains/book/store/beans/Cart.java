package com.geekbrains.book.store.beans;


import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.OrderItem;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Cart {
    List<OrderItem> items = new ArrayList<>();

    private BigDecimal totalPrice;

    public void addToCart(Book book) {
        if (book == null) {
            throw new IllegalArgumentException();
        }
        items.stream()
                .filter(item -> item.getBook().equals(book))
                .findFirst()
                .ifPresentOrElse(item -> {
                            item.setQuantity(item.getQuantity() + 1);
                            item.setTotalPrice(
                                    item.getBook().getPrice()
                                            .multiply(BigDecimal.valueOf(item.getQuantity()))
                            );
                        },
                        () -> items.add(new OrderItem(book))
                );
        refreshPrice();
    }

    public void setQuantityOfOrderItem(Book book, int quantity) {
        if (book == null || quantity < 0) {
            throw new IllegalArgumentException();
        }
        if (quantity == 0) {
            removeFromCart(book.getId());
            return;
        }

        items.stream()
                .filter(item -> item.getBook().equals(book))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setTotalPrice(
                            item.getBook().getPrice()
                                    .multiply(BigDecimal.valueOf(item.getQuantity()))
                    );
                });
        refreshPrice();
    }

    public void removeFromCart(Long bookId) {
                OrderItem item = items.stream().filter(book -> book.getId().equals(bookId)).findFirst().orElseThrow(IllegalArgumentException::new);
        items.remove(item);
        refreshPrice();
    }

    public void refreshPrice() {
        totalPrice = items.stream().map(OrderItem::getTotalPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }
}
