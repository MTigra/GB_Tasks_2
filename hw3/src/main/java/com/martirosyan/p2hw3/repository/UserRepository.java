package com.martirosyan.p2hw3.repository;

import com.martirosyan.p2hw3.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserRepository {
    private SessionFactory sf;

    public UserRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public List<User> findAll() {
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List<User> users = s.createQuery("select u from User u",User.class).getResultList();
        s.getTransaction().commit();
        return users;
    }
}
