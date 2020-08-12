package com.martirosyan.p2hw3.repository;

import com.martirosyan.p2hw3.model.Lot;
import org.hibernate.Session;

import javax.persistence.LockModeType;
import java.util.Random;

public class LotRepository {

    Random random = new Random();

    public Lot getLot(Session session, LockModeType lockModeType) {
        return session.createQuery("select l from Lot l WHERE l.id = :id", Lot.class)
                .setParameter("id", (long) random.nextInt(4) + 1)
                .setLockMode(lockModeType)
                .getSingleResult();
    }

}
