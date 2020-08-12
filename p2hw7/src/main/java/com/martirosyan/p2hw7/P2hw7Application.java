package com.martirosyan.p2hw7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class P2hw7Application {

    public static void main(String[] args) {
        SpringApplication.run(P2hw7Application.class, args);
    }

}
