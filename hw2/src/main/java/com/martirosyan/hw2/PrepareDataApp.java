package com.martirosyan.hw2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrepareDataApp {

    private SessionFactory factory;

    public PrepareDataApp() {
        this.factory = buildSessionFactory();
    }

    private SessionFactory buildSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public void prepareDb() {
        Session session = getSession();
        try {
            String sql = Files.lines(Paths.get("./hw2/data.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown();
            if (session != null) {
                session.close();
            }
        }
    }

    private void shutdown() {
        factory.close();
    }

}