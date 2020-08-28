package com.martirosyan.p2hw3.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbManager {

    private static SessionFactory sf = null;

    private DbManager() {
    }

    public static SessionFactory getSessionFactory() {
        if (sf == null) {

            synchronized (DbManager.class) {

                if (sf == null) {
                    sf = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .buildSessionFactory();
                }
            }
        }
        return sf;
    }
}
