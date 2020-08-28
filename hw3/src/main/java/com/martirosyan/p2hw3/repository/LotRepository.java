package com.martirosyan.p2hw3.repository;

import com.martirosyan.p2hw3.model.Lot;
import com.martirosyan.p2hw3.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.math.BigDecimal;
import java.util.Random;

public class LotRepository {

    private static Random random = new Random();
    private static SessionFactory sf;

    public LotRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public void raiseBid(User user, LockModeType lockModeType) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        try {

            Query<Lot> lotQuery = session.createQuery("select l from Lot l WHERE l.id = :id", Lot.class)
                    .setParameter("id", (long) random.nextInt(4) + 1)
                    .setLockMode(lockModeType);
            Lot lot = lockModeType == LockModeType.OPTIMISTIC ?
                    lotQuery.getSingleResult() :
                    lotQuery.setHint("javax.persistence.lock.timeout", 5000).getSingleResult();

            lot.setCurrent_price(lot.getCurrent_price().add(BigDecimal.valueOf(100)));
            lot.setUser(user);

            session.getTransaction().commit();
        } catch (OptimisticLockException e) {
            session.getTransaction().rollback();
            raiseBid(user, lockModeType);
        } finally {
            session.close();
        }
    }

    public BigDecimal getSumOfAllBids(){
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        BigDecimal sum = session.createQuery("select sum(current_price) from Lot ", BigDecimal.class).getSingleResult();
        session.getTransaction().commit();
        return sum;
    }

}
