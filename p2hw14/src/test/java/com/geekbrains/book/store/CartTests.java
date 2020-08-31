package com.geekbrains.book.store;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CartTests {
    @Autowired
    private Cart cart;

    @MockBean
    private BookService bookService;
    private List<Genre> genres1 = List.of(Genre.FANTASY,Genre.DETECTIVE);
    private List<Genre> genres2 = List.of(Genre.FANTASY);


    private Book bookOne = new Book(1L, "book1", "desc1", new BigDecimal("400.00"), 2000, genres1);
    private Book bookTwo = new Book(2L, "book2", "desc2", new BigDecimal("500.00"), 2010, genres2);

    private Book b = new Book();

    @BeforeEach
    public void setUp() {
        cart.setItems(new ArrayList<>());
        Mockito.doReturn(bookOne)
                .when(bookService)
                .findById(bookOne.getId());

        Mockito.doReturn(bookTwo)
                .when(bookService)
                .findById(bookTwo.getId());
    }

    @Test
    public void addOneBookToCartTest() {
        Assertions.assertAll(
                () -> {
                    cart.addToCart(bookOne);
                    OrderItem orderItemOne = cart.getItems().get(0);

                    Assertions.assertEquals(bookOne, orderItemOne.getBook());
                    Assertions.assertEquals(1, orderItemOne.getQuantity());
                    Assertions.assertEquals(bookOne.getPrice(), orderItemOne.getTotalPrice());
                    Assertions.assertEquals(bookOne.getPrice(), cart.getTotalPrice());
                }
        );
    }

    @Test
    public void addTwoSameBooksToCartTest() {
        Assertions.assertAll(
                () -> {
                    cart.addToCart(bookTwo);
                    cart.addToCart(bookTwo);
                    BigDecimal totalPrice = bookTwo.getPrice().add(bookTwo.getPrice());
                    Assertions.assertEquals(1, cart.getItems().size());
                    OrderItem orderItem = cart.getItems().get(0);

                    Assertions.assertEquals(bookTwo, orderItem.getBook());
                    Assertions.assertEquals(2, orderItem.getQuantity());
                    Assertions.assertEquals(totalPrice, orderItem.getTotalPrice());
                    Assertions.assertEquals(totalPrice, cart.getTotalPrice());
                });
    }

    @Test
    public void addTwoDiffBooksTest() {
        cart.addToCart(bookOne);
        cart.addToCart(bookTwo);
        BigDecimal totalPrice = bookOne.getPrice().add(bookTwo.getPrice());
        Assertions.assertEquals(2, cart.getItems().size());

        OrderItem item1 = cart.getItems().get(0);
        OrderItem item2 = cart.getItems().get(1);

        Assertions.assertEquals(totalPrice, cart.getTotalPrice());


    }

}
