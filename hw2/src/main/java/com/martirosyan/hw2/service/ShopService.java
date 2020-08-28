package com.martirosyan.hw2.service;

import com.martirosyan.hw2.repository.ShopRepository;

public class ShopService {

    ShopRepository repository;

    public ShopService(ShopRepository shopRepository) {
        repository = shopRepository;
    }

public String showProductsByConsumer(String params){
        return repository.findProductsByConsumerName(params).toString();
}

    public String showConsumersByProductTitle(String params) {
        return repository.findConsumersByProductTitle(params).toString();
    }

    public String deleteConsumer(String params) {
        repository.deleteConsumer(params);
        return "Успешно удален пользователь с именем " + params;
    }

    public String deleteProduct(String params) {
        repository.deleteProduct(params);
        return "Успешно удален продукт с именем " + params;
    }

    public String buy(String params) {
        String[] paramsarr = params.split(" ");
        repository.add(Long.parseLong(paramsarr[0]), Long.parseLong(paramsarr[1]));
        return "успешно добавлена запись";
    }
}
