package com.martirosyan.p2hw3.repository;

import com.martirosyan.p2hw3.model.User;
import org.hibernate.Session;

import java.util.List;

public class UserRepository {

    public List<User> findAll(Session session) {
        List<User> users = session.createQuery("select u from User u").getResultList();
        return users;
    }
}
