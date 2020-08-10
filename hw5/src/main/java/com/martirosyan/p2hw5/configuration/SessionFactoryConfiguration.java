package com.martirosyan.p2hw5.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


@Configuration
@ComponentScan(basePackages = {"com.martirosyan.p2hw5"})
public class SessionFactoryConfiguration {
    @Bean
    public SessionFactory sessionFactory() throws IOException {
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        initDb(sessionFactory);

        return sessionFactory;
    }

    private void initDb(SessionFactory sessionFactory) throws IOException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String ss = Files.lines(Paths.get("hw5/src/main/resources/data.sql"))
                .collect(Collectors.joining());
        int q = session.createNativeQuery(ss).executeUpdate();
        session.getTransaction().commit();
    }


}
