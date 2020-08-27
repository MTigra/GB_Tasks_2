package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {
    private BookService bookService;
    private Cart cart;

    public CartController(BookService bookService, Cart cart) {
        this.bookService = bookService;
        this.cart = cart;
    }

    @GetMapping
    public String showCart() {
        return "cart-page";
    }

    @GetMapping("/add/{id}")
    public String addBook(@PathVariable Long id) {
        cart.addToCart(bookService.findById(id));
        return "redirect:/cart";
    }

    @GetMapping("/setquantity/{id}")
    public String setQuantity(@PathVariable Long id, @RequestParam int quantity) {
        Book book = bookService.findById(id);
        cart.setQuantityOfOrderItem(book, quantity);
        return "redirect:/cart";

    }

    @GetMapping("/remove/{id}")
    public String setQuantity(@PathVariable Long id) {
        cart.removeFromCart(id);
        return "redirect:cart";

    }
}
