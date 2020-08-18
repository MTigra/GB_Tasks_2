package com.martirosyan.p2hw3;

import com.martirosyan.p2hw3.db.DbManager;
import com.martirosyan.p2hw3.model.User;
import com.martirosyan.p2hw3.repository.LotRepository;
import com.martirosyan.p2hw3.repository.UserRepository;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainApp {

    UserRepository userRepository;
    LotRepository lotRepository;
    private LockModeType lockmode;

    public static void main(String[] args) {
        PrepareDataApp prep = new PrepareDataApp();
        prep.prepareDb();
        MainApp app = new MainApp();
        // за 4880 ms Пессимистичная блокировка
        // LockModeType lockModeType = LockModeType.PESSIMISTIC_WRITE;
        // за 7615 ms Оптимистичная блокировка
        LockModeType lockModeType = LockModeType.OPTIMISTIC;
        // Сначала подумал что сделал что-то не так, раз такие результаты.
        // но потом подумал что у нас всего 4 лота, 8 пользователей, и вероятность того что они пытаются
        // повысить ставку на один и тот же лот достаточно высока, а роллбек делать дорого...
        long time = app.start(lockModeType);
        System.out.println("При " + lockModeType + " справились за " + time + " ms");
        System.out.println("Сумма: " + app.lotRepository.getSumOfAllBids());


    }

    public long start(LockModeType lockmode) {
        userRepository = new UserRepository(DbManager.getSessionFactory());
        lotRepository = new LotRepository(DbManager.getSessionFactory());
        this.lockmode = lockmode;
        return run();
        //return run(LockModeType.PESSIMISTIC_WRITE);
    }


    private long run() {

        List<User> users = userRepository.findAll();

        List<Thread> threads = users.stream().map(u -> new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lotRepository.raiseBid(u, lockmode);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).collect(Collectors.toList());


        ExecutorService es = Executors.newFixedThreadPool(8);

        long time = new Date().getTime();
        threads.forEach(es::execute);
        es.shutdown();
        try {
            es.awaitTermination(4l, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long resTime = new Date().getTime() - time;
        return resTime;
    }
}
