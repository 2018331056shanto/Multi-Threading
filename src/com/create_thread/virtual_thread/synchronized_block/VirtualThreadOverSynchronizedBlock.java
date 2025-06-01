package com.create_thread.virtual_thread.synchronized_block;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Demonstrates virtual thread pinning in JDK 21 when using a synchronized block with blocking operations.
 *
 * Author: Ashraful Islam Shanto
 * Date: 5/23/25
 * Time: 7:48 PM
 */
public class VirtualThreadOverSynchronizedBlock {

    // Shared lock object to force monitor contention and pinning
    private static final Object sharedLock = new Object();

    private static void cpuBoundWork() {
        long result = 0;
        for (int i = 0; i < 10000; i++) {
            result += i;
        }
    }

    public static void main(String[] args) {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");

        final int numberOfVirtualThreads = 1000000; // Don't use 1 million — just enough to show pinning effect
        System.out.println("Running with Java version: " + System.getProperty("java.version"));


        ThreadFactory threadFactory = Thread.ofVirtual().factory();
        ExecutorService virtualThreadExecutor = Executors.newThreadPerTaskExecutor(threadFactory);

        Instant startTime = Instant.now();

        for (int i = 0; i < numberOfVirtualThreads; i++) {

            final Object obj = new Object();
            virtualThreadExecutor.submit(() -> {
                cpuBoundWork();
                try {
                    cpuBoundWork();

                    // Synchronized on shared object -> causes pinning
                    synchronized (sharedLock) {
                        Thread.sleep(1000); // Blocking call inside synchronized block => triggers pinning
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        virtualThreadExecutor.shutdown();

        Instant endTime = Instant.now();
        System.out.println("Time to submit tasks: " + Duration.between(startTime, endTime).toMillis() + "ms");


    }
}
