package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import com.rabbitmq.client.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService usersService;
    private OrderService ordersService;
    private Cart cart;
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_FOR_PROCESSING_ORDERS = "ordersToProcess";
    private static final String EXCHANGE_FOR_PROCESSED_ORDERS = "processedOrders";


    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = usersService.findByUsername(principal.getName()).get();
        model.addAttribute("userinfo", user);
        Order order = new Order(user, cart.getItems());
        cart.getItems().clear();
        order.setStatus("В обработке");
        System.out.println("fff");
        ordersService.save(order);
        // в ребитмк
        rabbitTemplate.convertAndSend(EXCHANGE_FOR_PROCESSING_ORDERS, "", order.getId().toString());
        return "order-success";
    }




}
