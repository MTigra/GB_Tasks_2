package com.martirosyan.hw2.repository;

import com.martirosyan.hw2.model.Consumer;
import com.martirosyan.hw2.model.Product;
import com.martirosyan.hw2.model.Purchase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ShopRepository {
    private final SessionFactory sf;

    public ShopRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public ShopRepository() {
        sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public List<Product> findProductsByConsumerName(String name) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session
                .createQuery(
                        "select pr from Product pr, Purchase p where p.consumer.name=:name and pr.id=p.product.id", Product.class)
                .setParameter("name", name)
                .getResultList();
        session.getTransaction().commit();
        return products;
    }

    public List<Consumer> findConsumersByProductTitle(String name) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        List<Consumer> consumers = session
                .createQuery(
                        "select c from Consumer c, Purchase p where p.product.name=:name and c.id=p.consumer.id", Consumer.class)
                .setParameter("name", name)
                .getResultList();
        session.getTransaction().commit();
        return consumers;
    }

    public void deleteConsumer(String name) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Consumer where name=:name")
                .setParameter("name", name)
                .executeUpdate();
        session.getTransaction().commit();
    }

    public void deleteProduct(String name) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Product where name=:name")
                .setParameter("name", name)
                .executeUpdate();
        session.getTransaction().commit();
    }

    public void add(Long productId, Long consumerId) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Product p = session.find(Product.class, productId);
        Consumer c = session.find(Consumer.class, consumerId);
        session.save(new Purchase(p, c, p.getPrice()));
        session.getTransaction().commit();
    }


}
