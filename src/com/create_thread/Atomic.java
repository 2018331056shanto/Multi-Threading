package com.create_thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/5/2025<br>
 * Time:12:17 PM
 */

class SharedCounter {
    private final AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}

public class Atomic {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread-1 started");
            for (int i = 0; i < 50000; i++) {
                sharedCounter.increment();
            }
            System.out.println("Thread-1 finished");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread-2 started");
            for (int i = 0; i < 50000; i++) {
                sharedCounter.increment();
            }
            System.out.println("Thread-2 finished");

        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(sharedCounter.getValue());
    }

}
