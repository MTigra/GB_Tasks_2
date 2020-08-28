package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;


    @Transactional
    public void save(Order order) {
        repository.save(order);
    }

    public Optional<Order> findById(Long id){
        return repository.findById(id);
    }
}
