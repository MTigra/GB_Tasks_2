package com.martirosyan.p2hw3.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DbInitializer {

   public void initialize(){
       SessionFactory factory = new Configuration()
               .configure("hibernate.cfg.xml")
               .buildSessionFactory();
       Session session = null;
       try {
           String sqlStr = Files.lines(Paths.get("src\\main\\resources\\init.sql"))
                   .collect(Collectors.joining());
           session = factory.openSession();
           session.beginTransaction();
           session.createNativeQuery(sqlStr).executeUpdate();
           session.getTransaction().commit();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (session != null) {
               session.close();
           }
           factory.close();
       }
    }
}
