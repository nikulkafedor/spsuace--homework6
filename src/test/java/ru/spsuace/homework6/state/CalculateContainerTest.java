package ru.spsuace.homework6.state;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculateContainerTest {

    @Test
    public void deadlockTest() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1_000_000; i++) {
            System.out.println("i = " + i);
            CalculateContainer<Double> container = new CalculateContainer<>(10d);
            for (int j = 0; j < 10; j++) {
                service.execute(() -> container.finish(value -> System.out.println("finish " + value)));
                service.execute(() -> container.init(Math::sqrt));
                service.execute(() -> container.run((start, param) -> start + param, 5d));
            }
            container.close(value -> System.out.println("close " + value));
            assertEquals(State.CLOSE, container.getState());
        }

    }

}