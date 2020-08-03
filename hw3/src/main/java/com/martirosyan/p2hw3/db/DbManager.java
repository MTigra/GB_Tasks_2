package com.martirosyan.p2hw3.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbManager {

    public Session getSession() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        session = factory.openSession();
        return session;
    }
}
