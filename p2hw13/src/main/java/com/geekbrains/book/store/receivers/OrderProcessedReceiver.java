package com.geekbrains.book.store.receivers;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.services.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessedReceiver {

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = "#{queue1.name}")
    public void receive(String msg) throws InterruptedException {
        Long id = Long.parseLong(msg);
        Order order = orderService.findById(id).orElseThrow(IllegalArgumentException::new);
        order.setStatus("Обработан");
        orderService.save(order);
    }
}
