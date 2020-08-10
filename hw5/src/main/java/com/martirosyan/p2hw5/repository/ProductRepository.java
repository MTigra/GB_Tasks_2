package com.martirosyan.p2hw5.repository;

import com.martirosyan.p2hw5.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long save(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(product);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public Product findById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("SELECT p FROM products p", Product.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return products;
    }


}
