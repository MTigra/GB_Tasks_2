package com.martirosyan.p2hw3;

import com.martirosyan.p2hw3.db.DbInitializer;
import com.martirosyan.p2hw3.db.DbManager;
import com.martirosyan.p2hw3.model.Lot;
import com.martirosyan.p2hw3.model.User;
import com.martirosyan.p2hw3.repository.LotRepository;
import com.martirosyan.p2hw3.repository.UserRepository;
import org.hibernate.Session;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainApp {

    AtomicInteger aint = new AtomicInteger(0);
    UserRepository userRepository;
    LotRepository lotRepository;

    public static void main(String[] args) {
        System.out.println(new MainApp().start());

    }

    public long start() {
        DbManager dbManager = new DbManager();
        userRepository = new UserRepository();
        lotRepository = new LotRepository();
        return run(dbManager, LockModeType.OPTIMISTIC);
    }

    private long run(DbManager dbManager, LockModeType lockModeType) {
        DbInitializer dbInitializer = new DbInitializer();
        dbInitializer.initialize();
        Session userSession = dbManager.getSession();
        userSession.beginTransaction();
        List<User> users = userRepository.findAll(userSession);
        userSession.getTransaction().commit();
        userSession.close();

        List<Thread> threads = users.stream().map(u -> new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try (Session session = dbManager.getSession()) {
                    session.beginTransaction();
                    Lot lot = lotRepository.getLot(session, lockModeType);
                    lot.setCurrent_price(lot.getCurrent_price().add(BigDecimal.valueOf(100)));
                    lot.setUser(u);
                    session.save(lot);
                    session.getTransaction().commit();
                    session.close();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).collect(Collectors.toList());


        ExecutorService es = Executors.newFixedThreadPool(8);

        long time = new Date().getTime();
        threads.forEach(Thread::start);
        es.shutdown();
        return new Date().getTime() - time;
    }
}
