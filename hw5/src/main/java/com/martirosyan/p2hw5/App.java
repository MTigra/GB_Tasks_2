package com.martirosyan.p2hw5;

import com.martirosyan.p2hw5.configuration.SessionFactoryConfiguration;
import com.martirosyan.p2hw5.model.Product;
import com.martirosyan.p2hw5.model.User;
import com.martirosyan.p2hw5.service.ProductService;
import com.martirosyan.p2hw5.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    private static UserService userService;
    private static ProductService productService;

//    public App(UserService userService, ProductService productService) {
//        this.userService = userService;
//        this.productService = productService;
//    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SessionFactoryConfiguration.class);
        userService = context.getBean(UserService.class);
        productService = context.getBean(ProductService.class);

        User user1 = new User("u1");
        User user2 = new User(2, "u2");
        Product p1 = new Product("p1", 132);
        Product p2 = new Product(2, "p2", 100.5);
        productService.save(p1);
        productService.save(p2);
        userService.save(user1);
        userService.save(user2);

        productService.getAll().forEach(System.out::println);
        System.out.println(productService.findById(2l));

        userService.getAll().forEach(System.out::println);
        System.out.println(userService.findById(2l));

    }
}
