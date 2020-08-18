package com.martirosyan.hw2.controller;

import com.martirosyan.hw2.repository.ShopRepository;
import com.martirosyan.hw2.service.ShopService;

public class ShopController {
    ShopRepository repository;
    ShopService service;

    public ShopController() {
        repository = new ShopRepository();
        service = new ShopService(repository);
    }

    public void execute(String command, String params) {
        if (command.startsWith("/showProductsByConsumer")) {
            System.out.println(service.showProductsByConsumer(params));
        }
        if (command.equals("/showConsumersByProductTitle")) {
            System.out.println(service.showConsumersByProductTitle(params));
        }
        if (command.equals("/deleteConsumer")) {
            System.out.println(service.deleteConsumer(params));
        }
        if (command.equals("/deleteProduct")) {
            System.out.println(service.deleteProduct(params));
        }
        if (command.equals("/buy")) {
            System.out.println(service.buy(params));
        }
    }
}
