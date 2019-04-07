package ru.spsuace.homework6.account;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountManagerTest {

    private final AccountManager manager = new AccountManager();

    @Test
    public void simpleTest() {
        Account first = new Account("first", 1_000_000);
        Account second = new Account("second", 1_000_000);
        assertTrue(manager.transfer(first, second, 100_000));
        assertEquals(900_000, first.getBalance());
        assertEquals(1_100_000, second.getBalance());
    }

    @Test
    public void deadlockTest() throws InterruptedException {
        Account first = new Account("first", 1_000_000);
        Account second = new Account("second", 1_000_000);
        Executor executor = Executors.newFixedThreadPool(10);
        int maxIteration = 1_000_000;
        CountDownLatch latch = new CountDownLatch(maxIteration * 2);
        for (int i = 0; i < maxIteration; i++) {
            System.out.println("i = " + i);
            executor.execute(() -> {
                if (manager.transfer(first, second, 1)) {
                    latch.countDown();
                }

            });
            executor.execute(() -> {
                if (manager.transfer(second, first, 1)) {
                    latch.countDown();
                }
            });
        }
        assertEquals(1_000_000, first.getBalance());
        assertEquals(1_000_000, second.getBalance());
        assertTrue(latch.await(60, TimeUnit.SECONDS));
    }

}